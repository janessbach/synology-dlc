package controllers

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.{Inject, Singleton}

import modules.core.controllers.CoreController

@Singleton
class SettingsController @Inject()()(implicit exec: ExecutionContext) extends CoreController {

  def settings = BaseAction { implicit context => Future.successful(Ok(views.html.platform.settings())) }

}
