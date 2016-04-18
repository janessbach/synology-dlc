package modules.synology.client

import java.net.URLEncoder

import com.google.inject.Inject
import modules.core.auth.models.{LoginStatus, LogoutStatus}
import modules.synology.models.downloads.{DownloadStatus, Downloads}
import play.api.Configuration
import play.api.libs.json._
import play.api.libs.ws.WSClient
import play.utils.UriEncoding

import scala.concurrent.{ExecutionContext, Future}

class SynologyClient @Inject()(wsClient : WSClient,
                               config: SynologyClientConfiguration)(implicit ec: ExecutionContext) {

  private val ApiPrefix = "http://192.168.1.2:5000"

  private def loginCall[A](username: String, password: String)(implicit reads: Reads[A]) = retrieve(
    uri = s"/webapi/auth.cgi?api=SYNO.API.Auth&version=2&method=login&account=${UriEncoding.encodePathSegment(username, "utf-8")}&passwd=${URLEncoder.encode(password, "utf-8")}&session=DownloadStation&format=cookie"
  )

  private def logoutCall[A](implicit reads: Reads[A]) = retrieve(
    uri = s"/webapi/auth.cgi?api=SYNO.API.Auth&version=1&method=logout&session=DownloadStation"
  )

  private val DownloadTaskUri = "/webapi/DownloadStation/task.cgi"

  def login(username: String, password: String) : Future[LoginStatus] = {

    import LoginStatus._

    loginCall(username, password) map { _ getOrElse NotLoggedIn }
  }

  def logout(loginStatus: LoginStatus) : Future[LogoutStatus] = {

    import LogoutStatus._

    logoutCall map { _ getOrElse LogoutDone }
  }

  def download(loginStatus: LoginStatus)(urls: List[String]): Future[DownloadStatus] = {
    val postData = Map(
      "version" -> Seq("3"),
      "method" -> Seq("create"),
      "uri" -> Seq(urls.mkString(","))
    )

    import DownloadStatus._

    post(DownloadTaskUri, loginStatus, postData) map(_ getOrElse DownloadStatus(success = false))
  }

  def listDownloads(loginStatus: LoginStatus): Future[Downloads] = {
    val postData = Map(
      "version" -> Seq("1"),
      "method" -> Seq("list")
    )

    import Downloads._

    post(DownloadTaskUri, loginStatus, postData) map (_ getOrElse Downloads(total = 0, files = Nil, success = false))
  }


  private def retrieve[A](uri: String)(implicit reads: Reads[A]) : Future[Option[A]] = wsClient
    .url(ApiPrefix + uri)
    .get()
    .map { response =>
      Json.parse(response.body).validate[A] match {
        case JsSuccess(v, path) => Some(v); case e: JsError => None
      }
    }

  private def post[A](uri: String, loginStatus: LoginStatus, postData: Map[String, Seq[String]])(implicit reads: Reads[A]) : Future[Option[A]] = wsClient
    .url(ApiPrefix + uri)
    .post(addDefaultPostData(loginStatus, postData))
    .map { response =>
      Json.parse(response.body).validate[A] match {
        case JsSuccess(v, path) => Some(v); case e: JsError => None
      }
    }

  private def addDefaultPostData(loginStatus: LoginStatus, postData: Map[String, Seq[String]]): Map[String, Seq[String]] =
    postData ++ Map(
      "api" -> Seq("SYNO.DownloadStation.Task"),
      "_sid" -> Seq(loginStatus.data.sid)
    )

}

class SynologyClientConfiguration @Inject()(config: Configuration)

