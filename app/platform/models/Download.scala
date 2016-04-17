package platform.models

import play.api.libs.json.Json

case class Download(id: String, size: String, status: String, title: String)

object Download {
  implicit val reads = Json.reads[Download]
  implicit val writes = Json.writes[Download]
}
