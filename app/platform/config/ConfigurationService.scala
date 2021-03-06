package platform.config

import java.io.File

import com.google.inject.{Inject, Singleton}
import modules.core.config.FileBasedConfigurationService

object FileProvider {
  private val currentDir = new File(".").getAbsolutePath
  val configurationFile = new File(currentDir + "/config.json")
}

/**
  * FileBased Configuration Service
  *
  * How to use:
  *    configurationService.set("user", ConfigValueFactory.fromAnyRef(context.user.asJsonString))
  */
@Singleton
class ConfigurationService @Inject()() extends FileBasedConfigurationService(file = FileProvider.configurationFile) with Constants {

  def getAs[A](key : String) : Option[A] = get(key).map(_.unwrapped().asInstanceOf[A])

  def hostIp: String = getAs[String](ConfigHostName) getOrElse "127.0.0.1"

  def hostPort: Int = getAs[Int](ConfigHostPort) getOrElse 5000

}

trait Constants {
  val ConfigHostName = "config.hostname"
  val ConfigHostPort = "config.port"
}


