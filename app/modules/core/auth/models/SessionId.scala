package modules.core.auth.models

import play.api.libs.json.Json

case class SessionId(sid: String)

object SessionId {
  implicit val writes = Json.writes[SessionId]
  implicit val reads = Json.reads[SessionId]

  val InvalidSession = SessionId("")
}

