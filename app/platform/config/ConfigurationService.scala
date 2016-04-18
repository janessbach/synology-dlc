package platform.config

import java.io.File

import modules.core.config.FileBasedConfigurationService

/**
  * FileBased Configuration Service
  *
  * How to use:
  *    configurationService.set("user", ConfigValueFactory.fromAnyRef(context.user.asJsonString))
  */
class ConfigurationService extends FileBasedConfigurationService(file = FileProvider.configurationFile)

object FileProvider {
  private val currentDir = new File(".").getAbsolutePath
  val configurationFile = new File(currentDir + "/config.json")
}