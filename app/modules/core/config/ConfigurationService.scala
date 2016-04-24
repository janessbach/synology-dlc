package modules.core.config

import java.io.File

import com.typesafe.config.{Config, ConfigFactory, ConfigRenderOptions, ConfigValue}
import org.apache.commons.io.FileUtils
import play.api.Logger

import scala.util.{Failure, Success, Try}

abstract class ConfigurationService[A] {
  def get(key : String) : Option[A]
  def set(key : String, value : A) : A
  def save : Boolean
}

class FileBasedConfigurationService(val file : File) extends ConfigurationService[ConfigValue] {

  private val logger = Logger(getClass)

  private var config: Config = Try(ConfigFactory.parseFile(file)) match {
    case Success(s) => s
    case Failure(ex) =>
      logger.error("could not initialize the configuration file", ex)
      throw ex
  }

  override def get(key: String): Option[ConfigValue] = Try(config getValue key).toOption

  override def set(key: String, value: ConfigValue): ConfigValue = {
    synchronized {
      config = config withValue (key, value)
      save
      value
    }
  }

  override def toString = config.root().render(ConfigRenderOptions.concise())

  override def save: Boolean = Try(FileUtils.write(file, toString, "utf-8", false)).isSuccess

}


