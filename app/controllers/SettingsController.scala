package controllers

import javax.inject.{Inject, Singleton}

import modules.core.controllers.CoreController

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SettingsController @Inject()()(implicit exec: ExecutionContext) extends CoreController {

  def settings = BaseAction { implicit context => Future.successful(Ok(views.html.platform.settings())) }

}
