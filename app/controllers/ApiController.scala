package controllers

import javax.inject.Inject

import akka.actor.ActorSystem
import akka.stream.Materializer
import modules.core.controllers.CoreController
import modules.core.notification.services.NotificationService
import modules.dlc.models.RemoteFile
import modules.dlc.services.{DlcExtractorService, RemoteFileServiceFactory}
import platform.services.DownloadService
import play.api.libs.json.{Json, _}
import play.api.mvc.{Action, AnyContent, _}

import scala.concurrent.{ExecutionContext, Future}

class ApiController @Inject()(notificationService: NotificationService,
                              remoteFileService: RemoteFileServiceFactory,
                              dlcExtractorService: DlcExtractorService,
                              downloadService: DownloadService)
                             (implicit exec: ExecutionContext, actorSystem: ActorSystem, materializer: Materializer) extends CoreController {

  private val HtmlDlcInputName = "dlc-file"

  def available(url: String) = BaseAction { implicit context =>
    remoteFileService.checkAvailability(RemoteFile.url(url)) map { available =>
      if (available)
        Ok(Json.toJson(available))
      else
        Gone
    }
  }

  def dlcDecrypt = BaseAction(parse.multipartFormData) { implicit context =>
    context.request.body.file(HtmlDlcInputName) map { item =>
      val file = item.ref.file
      Future.successful(Ok(Json.toJson(dlcExtractorService.extract(file))))
    } getOrElse Future.successful(BadRequest)
  }

  def downloads: Action[AnyContent] = BaseAction { implicit context =>
    downloadService.downloads(context.user) map {
      case Some(downloads) => Ok(Json.toJson(downloads))
      case _ => BadRequest
    }
  }

  def download(urls: List[String]) = BaseAction { implicit context =>
    downloadService.download(context.user)(urls) map {
      case status if status => Ok(Json.toJson(status))
      case _ => BadRequest
    }
  }

  def subscribe = WebSocket.accept[JsValue, JsValue] { request => notificationService.flow }

}

