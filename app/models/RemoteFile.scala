package models

import de.itgecko.dlc.decrypter.DLCFile

case class RemoteFile(name: String, url: String, available: Boolean = true)


object RemoteFile {

  def apply(file: DLCFile): RemoteFile = new RemoteFile(file.getFilename, file.getUrl)

  def apply(files: List[DLCFile]): List[RemoteFile] = files map apply

}