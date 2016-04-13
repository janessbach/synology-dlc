package controllers

import javax.inject._

import modules.core.auth.models.User
import modules.core.auth.services.AuthService
import modules.core.controllers.{CoreController, RequestContext}
import platform.services.PlatformAuthService
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, AnyContent, Result}

import scala.concurrent.{ExecutionContext, Future}

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
      userData => authService.login(userData.name, userData.password).map(checkLogin)
    )
  }

  def logout : Action[AnyContent] = BaseAction { implicit context =>
    authService.logout(context.user).map(_ => redirect(controllers.routes.AuthController.login()).withNewSession)
  }

  private def checkLogin(user : User)(implicit context: RequestContext[AnyContent]) : Result = user match {
    case user @ User(username, loginStatus) if loginStatus.success =>
      redirect(controllers.routes.HomeController.dashboard())
        .addingToSession(PlatformAuthService.UserSessionKey -> user.asJsonString)
        .flashing()     // user logged in
    case _ =>
      redirect(controllers.routes.AuthController.index())
        .flashing()     // Could not login with credentials
  }

}

case class UserForm(name: String, password: String)

object UserForm {
  val Username = "username"
  val Password = "password"

  val formMapping = Form(
    mapping(
      Username -> text,
      Password -> text
    )(UserForm.apply)(UserForm.unapply)
  )
}