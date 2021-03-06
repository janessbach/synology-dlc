package modules.dlc.utils

import java.io.File

import de.itgecko.dlc.decrypter.DLCDecrypter
import modules.dlc.models.RemoteFile
import play.api.Logger

import scala.collection.JavaConversions._
import scala.util.{Failure, Success, Try}

object DlcExtractorUtils {

  private val logger = Logger(DlcExtractorUtils.getClass)

  def extract(file: File): List[RemoteFile] = Try(DLCDecrypter.decrypt(file).getDlcFiles.toList) match {
    case Success(files) => files.map(file => RemoteFile(file.getFilename, file.getUrl))
    case Failure(error) => logger.error("could not extract dlc file", error); List.empty
  }

}