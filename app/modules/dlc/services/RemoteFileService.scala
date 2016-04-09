package modules.dlc.services

import javax.inject.Inject

import modules.dlc.models.RemoteFile
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

abstract class RemoteFileService @Inject() (wsClient : WSClient)(implicit ec: ExecutionContext) {

  def checkAvailability(file: RemoteFile): Future[Boolean]

  def mapping = {
    Map(
      "http://www.share-online.biz" -> new ShareOnlineFileService(wsClient),
      "http://ul.to" -> new UploadedToFileService(wsClient)
    )
  }

}
