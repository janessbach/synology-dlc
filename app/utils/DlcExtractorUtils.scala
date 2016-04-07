package utils

import models.DlcFile
import models.DlcFile._
import de.itgecko.dlc.decrypter.DLCDecrypter

import play.api.Logger
import scala.collection.JavaConversions._
import scala.util.{Failure, Success, Try}

import java.io.File

object DlcExtractorUtils {

  val logger = Logger(DlcExtractorUtils.getClass)

  def extract(file: File): List[DlcFile] = {

    Try(DLCDecrypter.decrypt(file).getDlcFiles.toList).map(apply(_)) match {
      case Success(v) => v
      case Failure(e) => logger.error("could not extract dlc file", e); List.empty
    }

  }

}