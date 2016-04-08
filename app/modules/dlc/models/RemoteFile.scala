package modules.dlc.models

import play.api.libs.json.Json

case class RemoteFile(fileName: String, url: String)

object RemoteFile {

  implicit val writes = Json.writes[RemoteFile]
  implicit val reads = Json.reads[RemoteFile]

}