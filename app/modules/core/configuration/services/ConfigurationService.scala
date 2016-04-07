package modules.core.configuration.services

import javax.inject.{Inject, Singleton}

import modules.core.configuration.interfaces.ConfigProvider
import modules.core.configuration.models.Configuration
import modules.core.utils.EnrichedRequest

@Singleton
class ConfigurationService @Inject() (provider: ConfigProvider) {
  def load[A](implicit context: EnrichedRequest[A]) : Configuration = provider load context
}

