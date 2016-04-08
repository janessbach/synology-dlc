package modules.core

import javax.inject.Inject

import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

import scala.async.Async.{async, await}
import scala.concurrent.{ExecutionContext, Future}


class CoreAction @Inject()(val messagesApi: MessagesApi)(implicit ec: ExecutionContext) extends I18nSupport {

  def apply[A](bp: BodyParser[A])(delegate: RequestContext[A] => Future[Result]): Action[A] = Action.async(bp) { request : Request[A] =>
    async {
      await(delegate(new RequestContext(new EnrichedRequest(request), request2Messages(request), None)))
    }
  }

}
