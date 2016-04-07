package modules.core

import play.api.Configuration

package object configuration {

  implicit class MandatoryOption[A](option: Option[A]) {
    def mandatoryGet(message: String = "") = option.getOrElse(throw new IllegalStateException(
        Some(message).map(_.trim).filter(_.nonEmpty).getOrElse("Could not get mandatory Element")
    ))
  }

  implicit class MandatoryConfiguration(configuration: Configuration) {

    type Path = String

    type Reader[V] = Path => Option[V]

    def getMandatoryString(path: Path): String = readMandatory(path, configuration.getString(_))

    def getMandatoryBoolean(path: Path): Boolean = readMandatory(path, configuration.getBoolean)

    def getMandatoryInt(path: Path): Int = readMandatory(path, configuration.getInt)

    def getMandatoryStringSeq(path: Path): Seq[java.lang.String] = readMandatory(path, configuration.getStringSeq)

    private def readMandatory[V](path: Path, reader: Reader[V]): V = reader(path).mandatoryGet(s"missing configuration for $path")

  }

}
