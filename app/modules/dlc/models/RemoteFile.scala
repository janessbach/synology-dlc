package modules.dlc.models

import play.api.libs.json.Json

case class RemoteFile(name: String, url: String)

object RemoteFile {

  implicit val writes = Json.writes[RemoteFile]
  implicit val reads = Json.reads[RemoteFile]

}