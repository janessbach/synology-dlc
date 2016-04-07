package controllers

import javax.inject._

import models.RemoteFile
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  def index = Action {

    val files = List(RemoteFile("name1", "url1", false), RemoteFile("name2", "url2"))

    Ok(views.html.index(files))
  }

}
