package controllers

import javax.inject._

import modules.auth.models.User
import modules.auth.services.AuthService
import modules.core.request.{CoreController, RequestContext}
import play.api.mvc.{Action, AnyContent, Result}

import scala.concurrent.{ExecutionContext, Future}
import modules.core.result.ResultUtils._

@Singleton
class AuthController @Inject()(authService: AuthService)
                              (implicit exec: ExecutionContext) extends CoreController {

  def index : Action[AnyContent] = BaseAction { implicit context =>
    Future.successful(Ok(views.html.login("Login To Synology DLC")))
  }

  def login : Action[AnyContent] = BaseAction { implicit context =>
    import modules.auth.models.UserForm._
    formMapping.bindFromRequest.fold(
      formWithErrors => { Future.successful(BadRequest(views.html.login("Login To Synology DLC"), Some(formWithErrors))) },
      userData => authService.login(userData).map(user => login(user))
    )
  }

  private def login[A](user : User)(implicit context: RequestContext[A]) : Result =
    user.addToSession(Redirect("")).successFlashing(s"Hallo ${user.username}")

}
