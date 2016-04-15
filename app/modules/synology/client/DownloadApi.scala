package modules.synology.client

import com.google.inject.Inject
import modules.core.auth.models.LoginStatus
import modules.synology.models.downloads.{DownloadStatus, Downloads}
import play.api.libs.json.{JsError, JsSuccess, Json, Reads}
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

class DownloadApi @Inject() (wsClient : WSClient) (implicit ec: ExecutionContext) {

  private val ApiPrefix = "http://192.168.178.2:5000"

  def download(loginStatus: LoginStatus)(urls: List[String]): Future[DownloadStatus] = {
    val postData = Map(
      "version" -> Seq("3"),
      "method" -> Seq("create"),
      "uri" -> Seq(urls.mkString(","))
    )

    import DownloadStatus._

    post(loginStatus, postData) map(_ getOrElse DownloadStatus(success = false))
  }

  def listDownloads(loginStatus: LoginStatus): Future[Downloads] = {
    val postData = Map(
      "version" -> Seq("1"),
      "method" -> Seq("list")
    )

    import Downloads._

    post(loginStatus, postData) map (_ getOrElse Downloads(total = 0, files = Nil, success = false))
  }

  private def post[A](loginStatus: LoginStatus, postData: Map[String, Seq[String]])(implicit reads: Reads[A]) : Future[Option[A]] = wsClient
    .url(ApiPrefix + "/webapi/DownloadStation/task.cgi")
    .post(addDefaultPostData(loginStatus, postData))
    .map { response =>
      Json.parse(response.body).validate[A] match {
        case JsSuccess(v, path) => Some(v); case e: JsError => None
      }
    }

  private def addDefaultPostData(loginStatus: LoginStatus, postData: Map[String, Seq[String]]): Map[String, Seq[String]] = {
    val defaultData : Map[String, Seq[String]] = Map(
      "api" -> Seq("SYNO.DownloadStation.Task"),
      "_sid" -> Seq(loginStatus.data.sid)
    )

    defaultData ++ postData
  }

}
