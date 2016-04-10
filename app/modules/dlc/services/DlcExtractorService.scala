package modules.dlc.services

import java.io.File

import modules.dlc.models.RemoteFile
import modules.dlc.utils.DlcExtractorUtils

class DlcExtractorService  {

  def extract(file: File): List[RemoteFile] = DlcExtractorUtils.extract(file)

}