package controllers

import javax.inject._

import modules.core.request.CoreController

import scala.concurrent.Future

@Singleton
class HomeController @Inject() extends CoreController {

  def downloads = BaseAction { implicit context => Future.successful(Ok(views.html.dlc.downloads())) }

  def dashboard = BaseAction { implicit context => Future.successful(Ok(views.html.platform.dashboard())) }


}
