package modules.auth.services

import modules.auth.models.{User, UserForm}
import play.api.mvc.Request

import scala.concurrent.Future

trait AuthService {
  def login(userForm : UserForm) : Future[User]

  def loadFromSession[A](implicit request: Request[A]) : Option[User] = request.session
    .get(User.UserSessionKey)
    .flatMap(User.fromJson)
}


