package modules.dlc.models

import de.itgecko.dlc.decrypter.DLCFile
import play.api.libs.json.{Json, Writes}

case class RemoteFile(fileName: String, url: String)

object RemoteFile {

  implicit val writes = Json.writes[RemoteFile]
  implicit val reads = Json.reads[RemoteFile]



}