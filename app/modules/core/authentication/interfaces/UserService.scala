package modules.core.authentication.interfaces

import modules.core.authentication.models.User
import play.api.mvc.RequestHeader

import scala.concurrent.Future

trait UserService {

  def username(implicit request: RequestHeader): Option[String]

  def login(username: String, password : String): Future[Option[User]]

  def load(username: String): Future[Option[User]]

}
