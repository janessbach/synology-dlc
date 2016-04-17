package modules.synology.models.downloads

import play.api.libs.json.Json

case class DownloadStatus(success: Boolean)

object DownloadStatus {
  implicit val reads = Json.reads[DownloadStatus]
  implicit val writes = Json.writes[DownloadStatus]
}