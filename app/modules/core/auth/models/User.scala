package modules.core.auth.models

import play.api.libs.json.Json

case class User(username : String, loginStatus: LoginStatus) {
  def asJsonString : String = {
    implicit val writer = User.writes
    Json.stringify(Json.toJson(this))
  }
}

object User {
  implicit val reads = Json.reads[User]
  implicit val writes = Json.writes[User]

  val GuestUser = User("Guest", LoginStatus.NotLoggedIn)
}

