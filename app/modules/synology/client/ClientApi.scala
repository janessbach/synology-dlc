package modules.synology.client

import com.google.inject.Inject
import modules.core.auth.models.{LoginStatus, LogoutStatus}
import play.api.Configuration
import play.api.libs.json._
import play.api.libs.ws.WSClient
import play.utils.UriEncoding
import scala.concurrent.{ExecutionContext, Future}

class ClientApi @Inject() (wsClient : WSClient,
                           config: ClientApiConfiguration)(implicit ec: ExecutionContext) {

  private val ApiPrefix = "http://192.168.1.2:5000"

  private def loginCall[A](username: String, password: String)(implicit reads: Reads[A]) = retrieve(
    uri = s"/webapi/auth.cgi?api=SYNO.API.Auth&version=2&method=login&account=${UriEncoding.encodePathSegment(username, "utf-8")}&passwd=${UriEncoding.encodePathSegment(password, "utf-8")}&session=DownloadStation&format=cookie"
  )

  private def logoutCall[A](implicit reads: Reads[A]) = retrieve(
    uri = s"/webapi/auth.cgi?api=SYNO.API.Auth&version=1&method=logout&session=DownloadStation"
  )

  def login(username: String, password: String) : Future[LoginStatus] = {

    import LoginStatus._

    loginCall(username, password) map { _ getOrElse NotLoggedIn }
  }

  def logout(loginStatus: LoginStatus) : Future[LogoutStatus] = {

    import LogoutStatus._

    logoutCall map { _ getOrElse LogoutDone }
  }

  private def retrieve[A](uri: String)(implicit reads: Reads[A]) : Future[Option[A]] = wsClient
    .url(ApiPrefix + uri)
    .get()
    .map { response =>
      Json.parse(response.body).validate[A] match {
        case JsSuccess(v, path) => Some(v); case e: JsError => None
      }
    }
}

class ClientApiConfiguration @Inject()(config: Configuration)

