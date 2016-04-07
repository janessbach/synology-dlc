package modules.core.utils

import modules.core.authentication.models.User
import play.api.i18n.Messages
import play.api.mvc.RequestHeader

class RequestContext[+A](val request: EnrichedRequest[A], val messages : Messages, val user: Option[User] = None) {

  implicit val requestHeader : RequestHeader = request

  override def toString =
    s"""=================
       |${request.method} ${request.path}
       |=================
       |Headers: ${request.headers}
       |Identifier: ${request.buildIdentifier}
       |Language: ${messages.lang}
       |User: $user
       |=================""".stripMargin

}

