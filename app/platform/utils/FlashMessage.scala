package platform.utils

import modules.core.controllers.RequestContext
import play.api.libs.json.{JsResult, Json, Reads, Writes}

trait MessageLevel {
  def level : String
}

object Success extends MessageLevel {
  override def level : String = "success"
}

object Warning extends MessageLevel {
  override def level : String = "warning"
}

object Error extends MessageLevel {
  override def level : String = "danger"
}

case class FlashMessage(title: String, message : String, level : String)

case object FlashMessage {

  implicit val reads : Reads[FlashMessage] = Json.reads[FlashMessage]
  implicit val writes : Writes[FlashMessage] = Json.writes[FlashMessage]

  def flashMessages[A](implicit context : RequestContext[A]) : List[FlashMessage] = {
    val flashMap: Map[String, String] = context.request.flash.data
    flashMap.keySet.map { key =>
     Json.fromJson(Json.parse(flashMap(key)))(reads)
    } collect {
      case jsResult : JsResult[FlashMessage] if jsResult.isSuccess => jsResult.get
    }
  }.toList

}

abstract class Message(val titleKey: String, val messageKey: String)(level: MessageLevel = Success) {
  def fromMessageFile[A](implicit context: RequestContext[A]) = (context.messages(titleKey), context.messages(messageKey))

  def toJson[A](implicit context: RequestContext[A]) = {
    val (title, message) = fromMessageFile
    Json.stringify(Json.toJson(FlashMessage(title, message, level.level)))
  }

  def key : String = String.valueOf((titleKey + messageKey).hashCode)
}
