package controllers

import javax.inject.Inject

import akka.actor.ActorSystem
import akka.stream.Materializer
import modules.core.controllers.CoreController
import modules.core.notification.services.NotificationService
import modules.dlc.services.DlcExtractorService
import play.api.libs.json._
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class ApiController @Inject()(wsClient : WSClient,
                              notificationService: NotificationService,
                              dlcExtractorService: DlcExtractorService)
                             (implicit exec: ExecutionContext, actorSystem:ActorSystem, materializer: Materializer) extends CoreController {

  private val HtmlDlcInputName = "dlc-file"

  def dlcDecrypt = BaseAction(parse.multipartFormData) { implicit context =>
    context.request.body.file(HtmlDlcInputName) map { item =>
      Future.successful(Ok(Json.toJson(dlcExtractorService.extract(item.ref.file))))
    } getOrElse Future.successful(BadRequest)
  }

  def available = BaseAction { implicit context => Future.successful(Ok("")) }

  def subscribe = WebSocket.accept[JsValue, JsValue] { request => notificationService.flow }

}

