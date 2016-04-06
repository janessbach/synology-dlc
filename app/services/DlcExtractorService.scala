package services

import models.DlcFile
import java.io.File

import scala.collection.JavaConversions._
import de.itgecko.dlc.decrypter.DLCDecrypter
import play.api.Logger

import scala.util.Try

class DlcExtractorService {

  val logger = Logger(classOf[DlcExtractorService])

  def extract(file: File): List[DlcFile] = {

    Try(DLCDecrypter.decrypt(file).getDlcFiles).map { files =>
      files.map(file => new DlcFile(file.getFilename, file.getUrl))
    }.recover {
      case e: Exception =>
        logger.error(s"could not extract dlc file ${file.getAbsolutePath}", e)
        List.empty
    }.get.toList

  }

}