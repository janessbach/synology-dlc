package modules.dlc.services

import javax.inject.Inject

import modules.dlc.models.RemoteFile
import play.api.Logger
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

trait RemoteFileService {

  def urlStart: String
  def checkAvailability(file: RemoteFile): Future[Boolean]

}

class RemoteFileServiceFactory @Inject() (wsClient : WSClient,
                                          fileService: java.util.Set[RemoteFileService]) (implicit ec: ExecutionContext) {

  private val logger = Logger(classOf[RemoteFileServiceFactory])

  private val defaultFileService = new DefaultFileService(wsClient)

  private lazy val fileServices: Set[RemoteFileService] = {
    import scala.collection.JavaConversions._
    fileService.toSet
  }

  def checkAvailability(file: RemoteFile): Future[Boolean] = {
    val service = fileServices.find(service => file.url.startsWith(service.urlStart))

    service match {
      case Some(currentService) => currentService.checkAvailability(file)
      case _ => defaultFileService.checkAvailability(file)
    }
  }
}
