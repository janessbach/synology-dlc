package modules.core.message.services

import modules.core.controllers.RequestContext
import modules.core.message.models.Message
import play.api.http.HeaderNames
import play.api.libs.json.{JsResult, Json}
import play.api.mvc.{Cookie, Cookies, Flash, Result}

object FlashMessageServiceImplicits extends FlashMessageServiceImplicits

trait FlashMessageServiceImplicits {
  implicit class Adapter(result: Result) {
    def message[A](flashMessage: Message)(implicit context: RequestContext[A]) =
      new FlashMessageService().flashing(flashMessage)(result)(context)
  }
  implicit class AdapterContext[A](context: RequestContext[A]) {
    def flashMessages: List[Message] = new FlashMessageService().flashes(context)
  }
}

class FlashMessageService {

  def flashes[A](implicit context : RequestContext[A]) : List[Message] = {
    val flashMap: Map[String, String] = context.request.flash.data
    flashMap.keySet.map { key =>
      Json.fromJson(Json.parse(flashMap(key)))(Message.reads)
    } collect {
      case jsResult : JsResult[Message] if jsResult.isSuccess => jsResult.get
    }
  }.toList

  def flashing[A](flashMessage: Message)(result: Result)(implicit context: RequestContext[A]): Result =
    mergeFlash(flashMessage.localize)(result)

  private def flashCookie(result: Result): Option[Cookie] = result.header.headers
    .filter { case (name, value) => name == HeaderNames.SET_COOKIE && value.startsWith(Flash.COOKIE_NAME) }
    .get(HeaderNames.SET_COOKIE)
    .map(Cookies.decodeCookieHeader)
    .getOrElse(Nil)
    .headOption

  private def mergeFlash[A](message: Message)(result: Result)(implicit context: RequestContext[A]): Result =
    mergeFlash((message.key, Json.stringify(Json.toJson(message)(Message.writes))))(result)

  private def mergeFlash(flash: (String, String)*)(result: Result): Result = {
    val oldFlash = Flash.decodeFromCookie(flashCookie(result))
    result.flashing(oldFlash.copy(data = oldFlash.data ++ flash.toMap))
  }

}
