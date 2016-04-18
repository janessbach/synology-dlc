package modules.core.notification.models

import play.api.libs.json.{JsValue, Json, Reads, Writes}

case class Notification(receiver: String, message: JsValue)

object Notification {
  implicit val reads: Reads[Notification] = Json.reads[Notification]
  implicit val writes: Writes[Notification] = Json.writes[Notification]
}