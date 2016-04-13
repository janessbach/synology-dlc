package modules.core.network

import com.google.inject.Inject
import play.api.libs.ws.{WSClient, WSRequest}

class WebService extends WSClient {

  @Inject
  private val service : WSClient = null

  override def underlying[T]: T = service.underlying

  override def url(url: String): WSRequest = service.url(url)

  override def close(): Unit = service.close()

}
