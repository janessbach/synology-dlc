package modules.core.auth.models

import play.api.libs.json.Json

case class LoginStatus(data : SessionId, success: Boolean)

object LoginStatus {
  implicit val writes = Json.writes[LoginStatus]
  implicit val reads = Json.reads[LoginStatus]

  import SessionId._

  val NotLoggedIn = LoginStatus(success = false, data = InvalidSession)
}

