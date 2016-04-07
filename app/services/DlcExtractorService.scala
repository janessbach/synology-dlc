package services

import models.RemoteFile
import java.io.File

import utils.DlcExtractorUtils

class DlcExtractorService {

  def extract(file: File): List[RemoteFile] = DlcExtractorUtils.extract(file)

}