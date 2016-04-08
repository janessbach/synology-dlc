package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject() extends Controller {

  def index = Action {
    Ok(views.html.login("Login To Synology DLC"))
  }

  def downloads = Action {
    Ok(views.html.download.downloads())
  }

}
