package controllers

import javax.inject.Inject

import akka.actor.ActorSystem
import akka.stream.Materializer
import modules.core.controllers.CoreController
import modules.core.notification.services.NotificationService
import modules.dlc.models.RemoteFile
import modules.dlc.services.{DlcExtractorService, RemoteFileServiceFactory}
import modules.synology.services.SynologyService
import play.api.libs.json.{Json, _}
import play.api.mvc.{Action, AnyContent, _}

import scala.concurrent.{ExecutionContext, Future}

class ApiController @Inject()(notificationService: NotificationService,
                              remoteFileService: RemoteFileServiceFactory,
                              dlcExtractorService: DlcExtractorService,
                              synologyService: SynologyService)
                             (implicit exec: ExecutionContext, actorSystem: ActorSystem, materializer: Materializer) extends CoreController {

  private val HtmlDlcInputName = "dlc-file"

  def addDownloads = BaseAction(parse.json[List[RemoteFile]]) { implicit context =>
    synologyService.addDownloads(context.body) map {
      case status if status => Ok(Json.toJson(status))
      case _ => BadRequest
    }
  }

  def available(url: String) = BaseAction { implicit context =>
    remoteFileService.checkAvailability(RemoteFile.url(url)) map { available =>
      if (available)
        Ok(Json.toJson(available))
      else
        Gone
    }
  }

  def currentDownloads: Action[AnyContent] = BaseAction { implicit context =>
    synologyService.currentDownloads(context.user) map {
      case Some(downloads) => Ok(Json.toJson(downloads))
      case _ => BadRequest
    }
  }

  def dlcDecrypt = BaseAction(parse.multipartFormData) { implicit context =>
    context.request.body.file(HtmlDlcInputName) map { item =>
      val file = item.ref.file
      Future.successful(Ok(Json.toJson(dlcExtractorService.extract(file))))
    } getOrElse Future.successful(BadRequest)
  }

  def subscribe = WebSocket.accept[JsValue, JsValue] { request => notificationService.flow }

}


