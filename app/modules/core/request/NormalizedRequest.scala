package modules.core.request

import play.api.mvc.RequestHeader

object NormalizedRequest {

  def apply(requestHeader: RequestHeader): RequestHeader = requestHeader.copy(
    path = if (requestHeader.path == "/") requestHeader.path else requestHeader.path.stripSuffix("/").replaceAll("//+", "/")
  )

}

