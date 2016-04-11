package modules.auth.models

import modules.core.request.RequestContext
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Result

import scala.util.Try

case class User(username : String, loginStatus: LoginStatus) {
  def addToSession[A](result: Result)(implicit requestContext: RequestContext[A]) : Result = result
    .addingToSession(User.UserSessionKey -> asJsonString)

  def asJsonString : String = {
    implicit val writer = User.writes
    Json.stringify(Json.toJson(this))
  }
}
object User {
  val UserSessionKey = "user.session"

  implicit val reads = Json.reads[User]
  implicit val writes = Json.writes[User]

  def fromJson(json: JsValue) : Option[User] = reads.reads(json).asOpt

  def fromJson(json: String) : Option[User] = Try(Json.toJson(json))
    .toOption
    .flatMap { jsValue => fromJson(jsValue) }

  val GuestUser = User("Guest", LoginStatus.NotLoggedIn)
}

