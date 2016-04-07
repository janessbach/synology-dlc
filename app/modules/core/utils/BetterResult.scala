package modules.core.utils

import play.api.http.HeaderNames
import play.api.mvc.{Cookies, Flash, Cookie, Result}

object ResultUtils {
  implicit class ResultImprovements(val result: Result) {
    import FlashMessage._

    def infoFlashing(message : String) = info(result, message)
    def errorFlashing(message : String) = error(result, message)
    def successFlashing(message : String) = success(result, message)

    lazy val flashCookie: Option[Cookie] = result.header.headers
      .filter { case (name, value) => name == HeaderNames.SET_COOKIE && value.startsWith(Flash.COOKIE_NAME) }
      .get(HeaderNames.SET_COOKIE)
      .map(Cookies.decodeCookieHeader)
      .getOrElse(Nil)
      .headOption
  }
}


