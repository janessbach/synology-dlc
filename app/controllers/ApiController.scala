package controllers

import javax.inject._
import modules.core.utils.CoreController
import modules.dlc.services.DlcExtractorService
import play.api.libs.json.Json
import scala.concurrent.{ExecutionContext, Future}
import scala.io.Source

@Singleton
class ApiController @Inject()(dlcExtractorService: DlcExtractorService)
                             (implicit exec: ExecutionContext) extends CoreController {

  private val HtmlDlcInputName = "dlc-file"

  def message = BaseAction(parse.multipartFormData) { implicit context =>
      context.request.body.file(HtmlDlcInputName) map { item =>

        val dlcFiles = dlcExtractorService.extract(item.ref.file)


        Future.successful(Ok(Json.toJson(dlcFiles)))

      } getOrElse Future.successful(BadRequest)
  }

}
