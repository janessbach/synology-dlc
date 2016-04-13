package modules.dlc.services

import modules.dlc.models.RemoteFile
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.Inject

import modules.core.network.WebService
import play.api.Logger

/**
  * This class uses the Share-online Linkcheck API to check the availability of a download link.
  * To check it a post request to http://api.share-online.biz/linkcheck.php with the link is made.
  *
  * The Linkcheck API response is in the following format:
  * $_UPLOAD_ID;$_STATUS;$_FILENAME;$_FILSIZE
  *   - $_UPLOAD_ID:  Upload-ID from checked link
  *   - $_STATUS:     "OK", "DELETED", "NOT FOUND"
  *   - $_FILESIZE:   Filesize in bytes
  */
class ShareOnlineFileService @Inject()(wsClient : WebService)(implicit ec: ExecutionContext) extends RemoteFileService {

  private val logger = Logger(classOf[ShareOnlineFileService])

  private val LinkCheckUrl = "http://api.share-online.biz/linkcheck.php"

  override def urlStart: String = "http://www.share-online.biz"

  override def checkAvailability(file: RemoteFile): Future[Boolean] = {
    val response: Future[WSResponse] = wsClient.url(LinkCheckUrl)
      .withHeaders("content-type" -> "application/x-www-form-urlencoded")
      .post(Map("links" -> Seq(file.url)))

    response.map { response =>
      val result = response.body.split(";")

      result.length match {
        case 4 => result(1) == "OK"
        case _ => logger.error("unknown response format: " + response.body)
          false
      }
    }
  }
}
