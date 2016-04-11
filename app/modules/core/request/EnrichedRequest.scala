package modules.core.request

import play.api.mvc.{Request, WrappedRequest}

class EnrichedRequest[+A](request: Request[A]) extends WrappedRequest(request)