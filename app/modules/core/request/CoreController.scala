package modules.core.request

import com.google.inject.Inject
import play.api.mvc._

import scala.concurrent.Future

class CoreController extends Controller {

  implicit def requestContext[A](implicit context: RequestContext[A]): Request[A] = context.request

  @Inject
  var actionBuilder: CoreAction = null

  def BaseAction(delegate: RequestContext[AnyContent] => Future[Result]) = actionBuilder(parse.anyContent)(userCheck = true)(delegate)

  def BaseAction[A](bp: BodyParser[A])(delegate: RequestContext[A] => Future[Result]) = actionBuilder(bp)(userCheck = true)(delegate)

  def BaseAction(userCheck : Boolean)(delegate: RequestContext[AnyContent] => Future[Result]) = actionBuilder(parse.anyContent)(userCheck)(delegate)

  def BaseAction[A](bp: BodyParser[A], userCheck : Boolean)(delegate: RequestContext[A] => Future[Result]) = actionBuilder(bp)(userCheck)(delegate)
}

