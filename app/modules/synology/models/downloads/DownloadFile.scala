package modules.synology.models.downloads

import play.api.libs.json.{JsPath, Json, Reads}
import play.api.libs.functional.syntax._

case class Transfer(sizeDownloaded : String,
                    sizeUploaded : String,
                    speedDownload : Int,
                    speedUpload : Int)

object Transfer {
  implicit val reads: Reads[Transfer] = (
    (JsPath \ "size_downloaded").read[String] and
      (JsPath \ "size_uploaded").read[String] and
      (JsPath \ "speed_download").read[Int] and
      (JsPath \ "speed_upload").read[Int]
    ) (Transfer.apply _)
  implicit val writes = Json.writes[Transfer]
}

case class DownloadFile(id: String, size: String, status: String, title: String, transfer: Transfer) {
  def isFinished : Boolean = status == "finished"

}

object DownloadFile {
  implicit val reads: Reads[DownloadFile] = (
      (JsPath \ "id").read[String] and
      (JsPath \ "size").read[String] and
      (JsPath \ "status").read[String] and
      (JsPath \ "title").read[String] and
      (JsPath \ "additional" \ "transfer").read[Transfer]
    ) (DownloadFile.apply _)
  implicit val writes = Json.writes[DownloadFile]
}

