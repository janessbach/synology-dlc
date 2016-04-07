package services

import models.RemoteFile
import java.io.File
import javax.inject.Inject

import utils.DlcExtractorUtils

class DlcExtractorService @Inject() (fileService: RemoteFileService) {

  def extract(file: File): List[RemoteFile] =
    DlcExtractorUtils.extract(file)
      .map(file => file.copy(available = fileService.checkAvailability(file)))


}