package services

import models.DlcFile
import java.io.File

import utils.DlcExtractorUtils

class DlcExtractorService {

  def extract(file: File): List[DlcFile] = DlcExtractorUtils.extract(file)

}