package services

import com.google.inject.Inject
import modules.auth.models.{User, UserForm}
import modules.auth.services.AuthService
import modules.synology.services.SynologyService

import scala.concurrent.{ExecutionContext, Future}

class SynologyAuthService @Inject()(synologyService : SynologyService)(implicit ec: ExecutionContext) extends AuthService {

  override def login(userForm: UserForm): Future[User] = synologyService
    .login(userForm.name, userForm.password)
    .map(status => User(userForm.name, status))

}
