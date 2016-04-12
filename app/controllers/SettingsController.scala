package controllers

import modules.core.request.CoreController
import scala.concurrent.Future
import javax.inject.{Inject, Singleton}

@Singleton
class SettingsController @Inject() extends CoreController {

  def settings = BaseAction { implicit context => Future.successful(Ok(views.html.core.settings())) }

}
