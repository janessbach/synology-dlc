package modules.dlc.models

import play.api.libs.json.Json

case class RemoteFile(name: String, url: String, available: Boolean = true)

object RemoteFile {

  implicit val writes = Json.writes[RemoteFile]
  implicit val reads = Json.reads[RemoteFile]

}