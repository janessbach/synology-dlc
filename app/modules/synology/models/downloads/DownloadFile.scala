package modules.synology.models.downloads

import play.api.libs.json.{JsPath, Json, Reads}
import play.api.libs.functional.syntax._

case class DownloadFile(id: String, size: Int, status: String, title: String)

object DownloadFile {

  implicit val reads: Reads[DownloadFile] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "size").read[Int] and
      (JsPath \ "status").read[String] and
      (JsPath \ "title").read[String]
    ) (DownloadFile.apply _)

  implicit val writes = Json.writes[DownloadFile]
}
