package modules.core.auth.models

import play.api.libs.json.Json

case class LogoutStatus(success: Boolean)

object LogoutStatus {
  val LogoutDone = LogoutStatus(success = true)
  implicit val reads = Json.reads[LogoutStatus]
  implicit val writes = Json.writes[LogoutStatus]
}
