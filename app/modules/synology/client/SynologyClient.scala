package modules.synology.client

import com.google.inject.Inject
import modules.core.auth.models.{LoginStatus, LogoutStatus}
import modules.synology.models.downloads.{DownloadStatus, Downloads}
import platform.config.ConfigurationService
import play.api.Logger
import play.api.libs.json._
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import play.utils.UriEncoding

import scala.concurrent.{ExecutionContext, Future}

class SynologyClientConfiguration @Inject()(config: ConfigurationService) {
  val Ip = config hostIp
  val Port = config hostPort
}

class SynologyClient @Inject()(wsClient : WSClient,
                               config: SynologyClientConfiguration)(implicit ec: ExecutionContext) extends Receivers {

  private val ApiPrefix = "http://" + config.Ip + ":" + config.Port

  private val logger = Logger(getClass)

  private def loginCall[A](username: String, password: String)(implicit reads: Reads[A]) = retrieve(
    uri = s"/webapi/auth.cgi?api=SYNO.API.Auth&version=2&method=login&account=${UriEncoding.encodePathSegment(username, "utf-8")}&passwd=${UriEncoding.encodePathSegment(password, "utf-8")}&session=DownloadStation&format=cookie"
  )

  private def logoutCall[A](implicit reads: Reads[A]) = retrieve(
    uri = s"/webapi/auth.cgi?api=SYNO.API.Auth&version=1&method=logout&session=DownloadStation"
  )

  private def currentDownloadsCall[A](loginStatus: LoginStatus)(implicit reads: Reads[A]) = retrieve(
    uri = s"/webapi/DownloadStation/task.cgi",
    postData = Some(Map(
      "version" -> Seq("1"),
      "api"     -> Seq("SYNO.DownloadStation.Task"),
      "method"  -> Seq("list")
    )),
    loginStatus = Some(loginStatus)
  )

  private def addDownloadsCall[A](loginStatus: LoginStatus)(urls: List[String])(implicit reads: Reads[A]) = retrieve(
    uri = s"/webapi/DownloadStation/task.cgi",
    postData = Some(Map(
      "version" -> Seq("3"),
      "api"     -> Seq("SYNO.DownloadStation.Task"),
      "method"  -> Seq("create"),
      "uri"     -> Seq(urls.mkString(",")
    ))),
    loginStatus = Some(loginStatus)
  )

  def login(username: String, password: String) : Future[LoginStatus] =
    loginCall(username, password)(LoginStatus.reads) map ( _ getOrElse LoginStatus.NotLoggedIn )

  def logout(loginStatus: LoginStatus) : Future[LogoutStatus] =
    logoutCall(LogoutStatus.reads) map ( _ getOrElse LogoutStatus.LogoutDone )

  def addDownloads(loginStatus: LoginStatus)(urls: List[String]): Future[DownloadStatus] =
    addDownloadsCall(loginStatus)(urls)(DownloadStatus.reads) map ( _ getOrElse DownloadStatus.Failed )

  def currentDownloads(loginStatus: LoginStatus): Future[Downloads] =
    currentDownloadsCall(loginStatus)(Downloads.reads) map (_ getOrElse Downloads.empty )

  private def retrieve[A](uri: String, loginStatus: Option[LoginStatus] = None, postData: Option[Map[String, Seq[String]]] = None)
                         (implicit reads: Reads[A]) : Future[Option[A]] = {

    def currentReceiver : Receiver = postData match {
      case Some(data) =>
        val loginSession = loginStatus.map(status => Map("_sid" -> Seq(status.data.sid))).getOrElse(Map.empty)
        postReceiver(loginSession ++ data)
      case _ => getReceiver
    }

    val url = wsClient.url(ApiPrefix + uri)

    currentReceiver.apply(url).map { response =>
      Json.parse(response.body).validate[A] match {
        case JsSuccess(value, path) =>
          logger.info("[OK] requesting url" + url + ". With response: " + value.toString)
          Some(value)
        case e: JsError =>
          logger.error("[FAIL] requesting url" + url + ". With error: " + e.toString)
          None
      }
    }
  }

}

trait Receivers {
  type Receiver = WSRequest => Future[WSResponse]
  def getReceiver : Receiver = { request => request.get() }
  def postReceiver(postData: Map[String, Seq[String]]) : Receiver = { request => request.post(postData) }
}



