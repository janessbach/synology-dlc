package controllers

import modules.core.request.CoreController
import play.api.i18n.{I18nSupport, MessagesApi}
import scala.concurrent.Future
import javax.inject._

@Singleton
class HomeController @Inject() (val messagesApi: MessagesApi) extends CoreController with I18nSupport {

  def downloads = BaseAction { implicit context => Future.successful(Ok(views.html.dlc.downloads())) }

  def dashboard = BaseAction { implicit context => Future.successful(Ok(views.html.platform.dashboard())) }


}
