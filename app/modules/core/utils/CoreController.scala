package modules.core.utils

import javax.inject.Inject

import modules.core.configuration.services.ConfigurationService
import play.api.mvc.{AnyContent, BodyParser, Controller, Result}

import scala.concurrent.Future

class CoreController extends Controller {

  @Inject
  var config: ConfigurationService = null
  @Inject
  var actionBuilder: CoreAction = null

  def BaseAction[A](delegate: RequestContext[AnyContent] => Future[Result]) = actionBuilder(parse.anyContent)(delegate)

  def BaseAction[A](bp: BodyParser[A])(delegate: RequestContext[A] => Future[Result]) = actionBuilder(bp)(delegate)

}
