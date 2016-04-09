package modules.core

import javax.inject.Inject

import play.api.mvc._

import scala.concurrent.Future

class CoreController extends Controller {

  implicit def requestContext[A](implicit context: RequestContext[A]): Request[A] = context.request

  @Inject
  var actionBuilder: CoreAction = null

  def BaseAction[A](delegate: RequestContext[AnyContent] => Future[Result]) = actionBuilder(parse.anyContent)(delegate)

  def BaseAction[A](bp: BodyParser[A])(delegate: RequestContext[A] => Future[Result]) = actionBuilder(bp)(delegate)

}

