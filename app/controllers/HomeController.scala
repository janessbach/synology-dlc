package controllers

import javax.inject._

import modules.core.controllers.CoreController

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HomeController @Inject()(implicit exec: ExecutionContext) extends CoreController {

  def downloads = BaseAction { implicit context => Future.successful(Ok(views.html.downloads.downloads())) }

  def dashboard = BaseAction { implicit context => Future.successful(Ok(views.html.platform.dashboard())) }

}
