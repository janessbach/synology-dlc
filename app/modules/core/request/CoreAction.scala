package modules.core.request

import com.google.inject.Inject
import modules.auth.models.User
import modules.auth.services.AuthService
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import scala.async.Async.{async, await}

class CoreAction @Inject()(val messagesApi: MessagesApi, authService: AuthService)(implicit ec: ExecutionContext) extends I18nSupport {

  def apply[A](bp: BodyParser[A])(userCheck : Boolean = true)(delegate: RequestContext[A] => Future[Result]): Action[A] = Action.async(bp) { implicit request : Request[A] =>
    async {
      if (userCheck) {
        authService.loadFromSession match {
          case Some(user) => await(delegate(new RequestContext(new EnrichedRequest(request), request2Messages(request), user)))
          case None => notAuthorized
        }
      } else {
        import User._
        await(delegate(new RequestContext(new EnrichedRequest(request), request2Messages(request), GuestUser)))
      }
    }
  }

  /**
    * Handler for not authorized requests.
    *
    * All requests to this platform have to be authorized.
    *
    * @return  redirect to login
    */
  private def notAuthorized = {
    import Results._
    Redirect("/login").withNewSession
  }

}
