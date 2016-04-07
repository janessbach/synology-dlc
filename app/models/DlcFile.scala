package models

case class DlcFile(fileName: String, url: String)


object DlcFile {

  def apply(file: de.itgecko.dlc.decrypter.DLCFile): DlcFile = new DlcFile(file.getFilename, file.getUrl)

  def apply(files: List[de.itgecko.dlc.decrypter.DLCFile]): List[DlcFile] = files.map(apply(_))

}