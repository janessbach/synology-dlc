package modules.auth.models

import modules.core.request.{EnrichedRequest, RequestContext}
import play.api.libs.json.Json
import play.api.mvc.Result

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

  def loadFromSession[A](implicit enrichedRequest: EnrichedRequest[A]) : Option[User] = ???
}

