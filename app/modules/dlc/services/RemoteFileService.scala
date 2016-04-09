package modules.dlc.services

import javax.inject.Inject

import modules.dlc.models.RemoteFile
import play.api.Logger
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

trait RemoteFileService {
  def checkAvailability(file: RemoteFile): Future[Boolean]
}

class RemoteFileServiceFactory @Inject() (wsClient : WSClient)(implicit ec: ExecutionContext) {

  private val logger = Logger(classOf[RemoteFileServiceFactory])

  def checkAvailability(file: RemoteFile): Future[Boolean] = {
    val fileService =  mapping.find { case(key,value) => file.url.startsWith(key) }.map(_._2)

    fileService.map { service =>
      service.checkAvailability(file)
    } getOrElse {
      logger.error(s"unsupported file hoster for url: ${file.url}")
      Future.failed(new UnsupportedFileHosterException)
    }
  }

  def mapping = {
    Map(
      "http://www.share-online.biz" -> new ShareOnlineFileService(wsClient),
      "http://ul.to" -> new UploadedToFileService(wsClient)
    )
  }
}

class UnsupportedFileHosterException extends Exception