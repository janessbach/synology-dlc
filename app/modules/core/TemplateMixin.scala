package modules.core

import modules.core.controllers.RequestContext
import play.api.i18n.Messages

object TemplateMixin {
  implicit def messages[A](implicit context: RequestContext[A]): Messages = context.request.messages
}