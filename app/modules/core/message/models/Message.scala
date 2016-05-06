package modules.core.message.models

import modules.core.controllers.RequestContext
import play.api.libs.json.Json

case class Message(titleKey: String, messageKey: String, level: String = "") {
  def localize[A](implicit context: RequestContext[A]): Message =
    copy(titleKey = context.messages(titleKey), messageKey = context.messages(messageKey), level = level)

  def key : String = String.valueOf((titleKey + messageKey).hashCode)
}

object Message {
  implicit val reads = Json.reads[Message]
  implicit val writes = Json.writes[Message]
}