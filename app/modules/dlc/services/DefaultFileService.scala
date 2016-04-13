package modules.dlc.services

import javax.inject.Inject

import modules.core.network.WebService
import modules.dlc.models.RemoteFile

import scala.concurrent.{ExecutionContext, Future}

class DefaultFileService @Inject() (wsClient : WebService)(implicit ec: ExecutionContext) extends RemoteFileService {

  override def urlStart: String = ""

  override def checkAvailability(file: RemoteFile): Future[Boolean] = wsClient
    .url(file.url).get().map { _.status == 200 }

}
