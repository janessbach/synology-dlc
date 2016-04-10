package modules.dlc.services

import play.api.libs.ws.WSClient
import javax.inject.Inject
import scala.concurrent.ExecutionContext

class UploadedToFileService @Inject() (wsClient : WSClient)(implicit ec: ExecutionContext) extends DefaultFileService(wsClient) {

  override def urlStart: String = "http://ul.to"

}
