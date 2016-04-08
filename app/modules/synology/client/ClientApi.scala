package modules.synology.client

import com.google.inject.Inject
import play.api.libs.json._
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

case class SessionId(sid: Option[String])
object SessionId {
  implicit val writes = Json.writes[SessionId]
  implicit val reads = Json.reads[SessionId]

  val InvalidSession = SessionId(None)
}

case class LoginStatus(success: Boolean, data : SessionId)
object LoginStatus {
  implicit val writes = Json.writes[LoginStatus]
  implicit val reads = Json.reads[LoginStatus]

  import SessionId._

  val NotLoggedIn = LoginStatus(success = false, data = InvalidSession)
}


class ClientApi @Inject() (wsClient : WSClient,
                           config: ClientApiConfiguration)(implicit ec: ExecutionContext)  {

  private val ApiPrefix = "http://myds.com:5000"

  private def loginCall[A](username: String, password: String)(implicit writes: Reads[A]) = retrieve(ApiPrefix +
    "/webapi/auth.cgi?api=SYNO.API.Auth&version=2&method=login&account=admin&passwd=12345&session=DownloadStation&format=cookie")

  def login(username: String, password: String) : Future[LoginStatus] = {

    import LoginStatus._

    loginCall(username, password) map { _ getOrElse NotLoggedIn }
  }

  def logout() = {

  }

  private def retrieve[A](uri: String)(implicit reads: Reads[A]) : Future[Option[A]] = wsClient.url(uri)
    .get().map { response =>
      Try(Json.toJson(response.body)).toOption match {
        case Some(jsValue) => reads.reads(jsValue) match {
          case JsSuccess(v, path) => Some(v)
          case e: JsError => None
        }
        case _ => None
      }
    }
}


