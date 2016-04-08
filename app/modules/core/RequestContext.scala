package modules.core

import modules.synology.models.User
import play.api.i18n.Messages
import play.api.mvc.RequestHeader


class RequestContext[+A](val request: EnrichedRequest[A], val messages : Messages, val user: Option[User] = None) {

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

