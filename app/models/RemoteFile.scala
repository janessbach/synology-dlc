package models

import de.itgecko.dlc.decrypter.DLCFile

case class RemoteFile(fileName: String, url: String)


object RemoteFile {

  def apply(file: DLCFile): RemoteFile = new RemoteFile(file.getFilename, file.getUrl)

  def apply(files: List[DLCFile]): List[RemoteFile] = files map apply

}