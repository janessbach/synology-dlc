package platform.utils

import modules.core.controllers.RequestContext
import play.api.http.HeaderNames
import play.api.mvc.{Cookie, Cookies, Flash, Result}

object ResultUtils {

  implicit class BetterResult(result: Result) {

    private val flashCookie: Option[Cookie] = result.header.headers
      .filter { case (name, value) => name == HeaderNames.SET_COOKIE && value.startsWith(Flash.COOKIE_NAME) }
      .get(HeaderNames.SET_COOKIE)
      .map(Cookies.decodeCookieHeader)
      .getOrElse(Nil)
      .headOption

    private def mergeFlash(flash: (String, String)*): Result = {
      val oldFlash = Flash.decodeFromCookie(flashCookie)
      result.flashing(oldFlash.copy(data = oldFlash.data ++ flash.toMap))
    }

    def flashing[A](flash: Message)(implicit context: RequestContext[A]): Result =
      mergeFlash((flash.key, flash.toJson))

    def flashing[A](flashes: List[Message])(implicit context: RequestContext[A]): Result =
      flashes.foldLeft(result)((a, i) => a.flashing(i)(context))

  }

}


