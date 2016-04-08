package modules.dlc.services

import java.io.File
import javax.inject.Inject

import modules.dlc.models.RemoteFile
import modules.dlc.utils.DlcExtractorUtils
import services.RemoteFileService

class DlcExtractorService @Inject() (fileService: RemoteFileService) {

  def extract(file: File): List[RemoteFile] =
    DlcExtractorUtils.extract(file)
      .map(file => file.copy(available = fileService.checkAvailability(file)))


}