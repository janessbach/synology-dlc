package controllers

import javax.inject._

import modules.core.{RequestContext, CoreController}
import modules.synology.client.ClientApi
import modules.synology.client.models.LoginStatus
import play.api.libs.json.Json
import play.api.mvc.Result

import scala.concurrent.{Future, ExecutionContext}

import play.api.data._
import play.api.data.Forms._

case class User(username : String, loginStatus: LoginStatus)
object User {
  implicit val reads = Json.reads[User]
  implicit val writes = Json.writes[User]
}

case class UserData(name: String, password: String)

object UserData {

  val Username = "username"
  val Password = "password"

  val formMapping = Form(
    mapping(
      Username -> text,
      Password -> text
    )(UserData.apply)(UserData.unapply)
  )
}

@Singleton
class AuthController @Inject()(client : ClientApi) (implicit exec: ExecutionContext) extends CoreController {

  def login = BaseAction { implicit context =>
    import UserData._
    formMapping.bindFromRequest.fold(
      formWithErrors => { Future.successful(Redirect("")) },
      userData => addToSession(userData)
    )
  }

  private def addToSession[A](userData: UserData)(implicit context: RequestContext[A]) : Future[Result] = {
    import Authenticate._
    client.login(userData.name, userData.password).map {
      case l @ LoginStatus(sessionId, true) => Redirect("").addingToSession(UserSessionKey -> Json.stringify(Json.toJson(User(userData.name, l))))(context.requestHeader)
      case _ => Redirect("")
    }
  }

}

object Authenticate {
  val UserSessionKey = "user.session"
}

