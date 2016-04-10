package modules.auth.services

import modules.auth.models.{User, UserForm}

import scala.concurrent.Future

trait AuthService {
  def login(userForm : UserForm) : Future[User]
}


