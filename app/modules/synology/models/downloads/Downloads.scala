package modules.synology.models.downloads

import play.api.libs.json.{JsPath, Json, Reads}
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

case class Downloads(total: Int, files: List[DownloadFile], success: Boolean)

object Downloads {

  implicit val reads: Reads[Downloads] = (
    (JsPath \ "data" \ "total").read[Int] and
      (JsPath \ "data" \ "tasks").read[List[DownloadFile]] and
      (JsPath \ "success").read[Boolean]
    ) (Downloads.apply _)

  implicit val writes = Json.writes[Downloads]
}
