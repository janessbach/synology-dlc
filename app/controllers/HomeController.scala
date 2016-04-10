package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject() extends Controller {

  def downloads = Action {
    Ok(views.html.download.downloads())
  }

}
