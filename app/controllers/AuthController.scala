package controllers

import javax.inject._

import modules.core.auth.services.AuthService
import modules.core.controllers.CoreController
import play.api.mvc.{Action, AnyContent}

import scala.concurrent.{ExecutionContext, Future}
import modules.platform.auth.models.UserForm
import modules.platform.auth.services.PlatformAuthService._

@Singleton
class AuthController @Inject()(authService: AuthService)
                              (implicit exec: ExecutionContext) extends CoreController {

  def index = BaseAction(userCheck = false) { implicit context =>
    Future.successful(Ok(views.html.platform.login("Login To Synology DLC")))
  }

  def login : Action[AnyContent] = BaseAction(userCheck = false) { implicit context =>
    import UserForm._
    formMapping.bindFromRequest.fold(
      formWithErrors => {
        val html = views.html.platform.login("Login To Synology DLC", Some(formWithErrors))
        Future.successful(BadRequest(html))
      },
      userData => authService
        .login(userData.name, userData.password)
        .map(user => redirect().addingToSession(UserSessionKey -> user.asJsonString))
    )
  }

  def logout : Action[AnyContent] = BaseAction { implicit context =>
    authService.logout(context.user).map(_ => redirect().withNewSession)
  }

}
