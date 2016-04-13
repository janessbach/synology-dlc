package controllers

import modules.core.request.CoreController
import play.api.i18n.{I18nSupport, MessagesApi}
import scala.concurrent.Future
import javax.inject.{Inject, Singleton}

@Singleton
class SettingsController @Inject() (val messagesApi: MessagesApi) extends CoreController with I18nSupport {

  def settings = BaseAction { implicit context => Future.successful(Ok(views.html.platform.settings())) }

}
