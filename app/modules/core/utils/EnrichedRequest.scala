package modules.core.utils

import modules.core.configuration.Platform._
import modules.core.configuration.models.Configuration
import play.api.mvc.{Request, WrappedRequest}

class EnrichedRequest[+A](request: Request[A]) extends WrappedRequest(request) {

  val config: Configuration = Configuration load this

  def buildIdentifier: String = request.host
    .replaceAll("www.", "")
    .replaceAll(":\\d+", "")
    .replaceAll("[^A-Za-z0-9]", "")

}


