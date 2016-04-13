package modules.dlc.services

import javax.inject.Inject

import modules.dlc.models.RemoteFile
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

class DefaultFileService @Inject()(wsClient : WSClient)(implicit ec: ExecutionContext) extends RemoteFileService {

  override def urlStart: String = ""

  override def checkAvailability(file: RemoteFile): Future[Boolean] = wsClient
    .url(file.url).get().map { _.status == 200 }

}
