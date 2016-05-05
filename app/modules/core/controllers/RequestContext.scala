package modules.core.controllers

import modules.core.auth.models.User
import play.api.i18n.{Lang, Messages}
import play.api.mvc.{Request, RequestHeader, WrappedRequest}

case class RequestContext[+A](request: EnrichedRequest[A],
                              user: User) extends WrappedRequest(request = request) {

  implicit val requestHeader : RequestHeader = request

  implicit val lang : Lang = request.messages.lang

  val messages = request.messages

  override def toString =
    s"""=================
       |${request.method} ${request.path}
       |=================
       |Headers: ${request.headers}
       |Language: $lang
       |User: $user
       |=================""".stripMargin

}

class EnrichedRequest[+A](request: Request[A])(val messages : Messages) extends WrappedRequest(request)



