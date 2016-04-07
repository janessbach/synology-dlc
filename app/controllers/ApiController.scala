package controllers

import akka.actor.ActorSystem
import javax.inject._
import modules.core.utils.CoreController
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ApiController @Inject()(actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends CoreController {

  private val HtmlDlcInputName = "dlc-file"

  def message = BaseAction(parse.multipartFormData) { implicit context =>
      context.request.body.file(HtmlDlcInputName) map { item =>

        Future.successful(Ok(""))

      } getOrElse Future.successful(BadRequest)
  }

}
