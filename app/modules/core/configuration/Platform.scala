package modules.core.configuration

import modules.core.configuration.services.ConfigurationService
import play.api.Play
import play.api.Play.current

object Platform {
  val Configuration : ConfigurationService = Play.application.injector.instanceOf(classOf[ConfigurationService])
}

