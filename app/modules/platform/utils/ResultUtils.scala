package modules.platform.utils

import play.api.mvc.Result

object ResultUtils {
  implicit class ResultImprovements(val result: Result) {
    import FlashMessage._

    def infoFlashing(message : String) = info(result, message)
    def errorFlashing(message : String) = error(result, message)
    def successFlashing(message : String) = success(result, message)
  }
}


