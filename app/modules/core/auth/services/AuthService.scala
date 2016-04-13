package modules.core.auth.services

import modules.core.auth.models.User
import play.api.mvc.Request

import scala.concurrent.Future

trait AuthService {
  def login(username: String, password: String) : Future[User]

  def loadUser[A](implicit request: Request[A]) : Option[User]

  def logout(user: User): Future[Boolean]
}

