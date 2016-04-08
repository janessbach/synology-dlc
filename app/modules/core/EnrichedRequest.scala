package modules.core

import play.api.mvc.{Request, WrappedRequest}

class EnrichedRequest[+A](request: Request[A]) extends WrappedRequest(request) {

}


