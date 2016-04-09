package modules.dlc.services

import javax.inject.Inject

import modules.dlc.models.RemoteFile
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.{ExecutionContext, Future}

class UploadedToFileService @Inject() (wsClient : WSClient)(implicit ec: ExecutionContext) extends RemoteFileService(wsClient) {

  override def checkAvailability(file: RemoteFile): Future[Boolean] = {

    val response: Future[WSResponse] = wsClient.url(file.url).get()

    response.map { response =>
      response.status == 200
    }
  }

}
