package modules.core

import play.api.i18n.Messages

object TemplateMixin {
  implicit def messages[A](requestContext: RequestContext[A]): Messages = requestContext.messages
}