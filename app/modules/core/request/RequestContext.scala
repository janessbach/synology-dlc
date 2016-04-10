package modules.core.request

import modules.auth.models.User
import play.api.i18n.Messages
import play.api.mvc.{RequestHeader, WrappedRequest}


class RequestContext[+A](val request: EnrichedRequest[A], val messages : Messages, val user: User) extends WrappedRequest(request = request) {

  implicit val requestHeader : RequestHeader = request

  override def toString =
    s"""=================
       |${request.method} ${request.path}
       |=================
       |Headers: ${request.headers}
       |Language: ${messages.lang}
       |User: $user
       |=================""".stripMargin

}

