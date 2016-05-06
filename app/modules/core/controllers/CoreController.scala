package modules.core.controllers

import com.google.inject.Inject
import modules.core.auth.models.User
import modules.core.auth.services.AuthService
import modules.core.message.services.FlashMessageServiceImplicits
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, _}

import scala.async.Async.{async, await}
import scala.concurrent.{ExecutionContext, Future}

class CoreController(implicit ec: ExecutionContext) extends
  Controller with I18nSupport
  with FlashMessageServiceImplicits {

  @Inject()
  val messagesApi: MessagesApi = null

  @Inject()
  val authService: AuthService = null

  var returnUrl : Option[String] = None

  def redirect(call : Call): Result = redirect(call.url)

  def redirect(url : String = "/"): Result = Redirect(returnUrl.getOrElse(url))

  def BaseAction(delegate: RequestContext[AnyContent] => Future[Result]): Action[AnyContent] =
    BaseAction(parse.anyContent, userCheck = true)(delegate)

  def BaseAction[A](bp: BodyParser[A])(delegate: RequestContext[A] => Future[Result]): Action[A] =
    BaseAction(bp, userCheck = true)(delegate)

  def BaseAction(userCheck : Boolean)(delegate: RequestContext[AnyContent] => Future[Result]): Action[AnyContent] =
    BaseAction(parse.anyContent, userCheck = userCheck)(delegate)

  def BaseAction[A](bp: BodyParser[A], userCheck : Boolean)(delegate: RequestContext[A] => Future[Result]): Action[A] = Action.async(bp) {
    implicit request : Request[A] =>
    async {
      if (userCheck) {
        authService.loadUser match {
          case Some(user) => await(delegate(buildRequestContext(user)))
          case None => notAuthorized
        }
      } else {
        import User._
        await(delegate(buildRequestContext(GuestUser)))
      }
    }
  }

  protected def buildRequestContext[A](user : User)(implicit request: Request[A]) =
    new RequestContext(new EnrichedRequest(request)(messagesApi preferred request), user)

  protected def notAuthorized : Result = Redirect("/")

  implicit def requestContext[A](implicit context: RequestContext[A]): Request[A] = context.request

}
