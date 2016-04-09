package controllers

import javax.inject._
import modules.core.CoreController
import modules.dlc.services.DlcExtractorService
import play.api.libs.json.Json
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ApiController @Inject()(dlcExtractorService: DlcExtractorService)
                             (implicit exec: ExecutionContext) extends CoreController {

  private val HtmlDlcInputName = "dlc-file"

  def dlcDecrypt = BaseAction(parse.multipartFormData) { implicit context =>
    context.request.body.file(HtmlDlcInputName) map { item =>
      Future.successful(Ok(Json.toJson(dlcExtractorService.extract(item.ref.file))))
    } getOrElse Future.successful(BadRequest)
  }

  def available = BaseAction { implicit context =>
    Future.successful(Ok(""))
  }

}

