package modules.platform.auth.services

import com.google.inject.{Inject, Singleton}
import modules.auth
import modules.core.auth.models.User
import modules.core.auth.services.AuthService
import modules.synology.services.SynologyService
import play.api.libs.json.Json
import play.api.mvc.Request

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PlatformAuthService @Inject()(synologyService : SynologyService)
                                   (implicit ec: ExecutionContext) extends AuthService {

  override def login(username: String, password: String): Future[User] = synologyService
    .login(username, password)
    .map(User(username, _))

  override def loadUser[A](implicit request: Request[A]): Option[User] =
    request.session.get(auth.UserSessionKey)
      .flatMap(Json.parse(_).asOpt[User])

  override def logout(user: User): Future[Boolean] = Future.successful(true) // FIXME: todo

}

object PlatformAuthService {
  val UserSessionKey = "user.session"
}