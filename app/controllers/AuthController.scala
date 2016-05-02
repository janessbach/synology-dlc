package controllers

import javax.inject._

import com.typesafe.config.ConfigValueFactory
import modules.core.auth.models.User
import modules.core.auth.services.AuthService
import modules.core.controllers.CoreController
import platform.config.{ConfigurationService, Constants}
import platform.services.PlatformAuthService
import platform.utils.FlashMessage
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.Messages
import play.api.mvc.{Action, AnyContent}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AuthController @Inject()(authService: AuthService,
                               configuration: ConfigurationService)
                              (implicit exec: ExecutionContext) extends CoreController with Constants {

  def index = BaseAction(userCheck = false) { implicit context =>
    val defaultValues = UserForm.formMapping.bind(
      Map(
        UserForm.Ip -> configuration.hostIp,
        UserForm.Port -> configuration.hostPort.toString
      )
    )
    Future.successful(Ok(views.html.platform.login(Some(defaultValues))))
  }

  def login : Action[AnyContent] = BaseAction(userCheck = false) { implicit context =>
    UserForm.formMapping.bindFromRequest.fold(
      formWithErrors => {
        val html = views.html.platform.login(Some(formWithErrors))
        Future.successful(BadRequest(html))
      },
      userData => {
        saveToConfiguration(userData)
        authService.login(userData.name, userData.password).map {
          case user @ User(username,loginStatus) if loginStatus.success =>
            FlashMessage.success(
              redirect(controllers.routes.HomeController.dashboard())
                .addingToSession(PlatformAuthService.UserSessionKey -> user.asJsonString),
              Messages("login.success")
            )
          case _ =>
            FlashMessage.error(
              redirect(controllers.routes.AuthController.index()),
              Messages("login.error")
            )
        }
      }
    )
  }

  private def saveToConfiguration(userData: UserForm) = {
    configuration.set(ConfigHostName, ConfigValueFactory fromAnyRef userData.ip)
    configuration.set(ConfigHostPort, ConfigValueFactory fromAnyRef userData.port)
  }

  def logout : Action[AnyContent] = BaseAction { implicit context =>
    authService.logout(context.user).map(_ => redirect(controllers.routes.AuthController.login()).withNewSession)
  }

}

case class UserForm(ip: String, port : Int, name: String, password: String)

object UserForm {
  val Ip = "ip"
  val Username = "username"
  val Password = "password"
  val Port = "port"

  val formMapping = Form(
    mapping(
      Ip -> text,
      Port -> number,
      Username -> text,
      Password -> text
    )(UserForm.apply)(UserForm.unapply)
  )
}