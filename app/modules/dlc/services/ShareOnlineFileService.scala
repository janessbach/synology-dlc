package modules.dlc.services

import modules.dlc.models.RemoteFile
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.Inject

import play.api.Logger

class ShareOnlineFileService @Inject() (wsClient : WSClient)(implicit ec: ExecutionContext) extends RemoteFileService {

  private val logger = Logger(classOf[ShareOnlineFileService])

  private val LinkCheckUrl = "http://api.share-online.biz/linkcheck.php"

  override def checkAvailability(file: RemoteFile): Future[Boolean] = {
    val response: Future[WSResponse] = wsClient.url(LinkCheckUrl)
      .withHeaders("content-type" -> "application/x-www-form-urlencoded")
      .post(Map("links" -> Seq(file.url)))

    response.map { response =>
      val result = response.body.split(";")

      // $_UPLOAD_ID;$_STATUS;$_FILENAME;$_FILSIZE

      result.length match {
        case 4 => result(1) == "OK"
        case _ => logger.error("unknown response format: " + response.body)
          false
      }
    }
  }
}
