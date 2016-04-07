package modules.core.configuration.interfaces

import modules.core.configuration.models.Configuration
import modules.core.utils.EnrichedRequest

trait ConfigProvider {
  def load[A](implicit context: EnrichedRequest[A]) : Configuration
}
