package models

import utils.DlcExtractorUtils
import java.io.File

case class DlcFile(fileName: String, url: String)


object DlcFile {

  def apply(file: File): List[DlcFile] = DlcExtractorUtils.extract(file)

}