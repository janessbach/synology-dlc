package controllers

import javax.inject._

import modules.dlc.models.RemoteFile
import play.api.mvc._

@Singleton
class HomeController @Inject() extends Controller {

  def index = Action {

    val files = List(RemoteFile("name1", "url1", false), RemoteFile("name2", "url2"))

    Ok(views.html.index(files))
  }

}
