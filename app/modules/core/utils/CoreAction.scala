package modules.core.utils

import javax.inject.Inject

import modules.core.authentication.interfaces.UserService
import modules.core.configuration.services.ConfigurationService
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

import scala.async.Async.{async, await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class CoreAction @Inject()(userService: UserService,
                           configService: ConfigurationService,
                           val messagesApi: MessagesApi) extends I18nSupport {

  def apply[A](bp: BodyParser[A])(delegate: RequestContext[A] => Future[Result]): Action[A] = Action.async(bp) { request : Request[A] =>
    async {
      val username = userService.username(request)
      val user = if (username.isDefined) { await(userService.load(username.get)) } else { None }

      await(delegate(new RequestContext(new EnrichedRequest(request), request2Messages(request), user)))
    }
  }

}
