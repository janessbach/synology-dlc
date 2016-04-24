package platform.jobs

import akka.actor.ActorSystem
import akka.stream.Materializer
import com.google.inject.{Inject, Singleton}
import modules.core.notification.models.Notification
import modules.core.notification.services.NotificationService
import play.api.libs.json.JsString
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._

@Singleton
class SynologyHostFinder @Inject()(actorSystem : ActorSystem,
                                   notificationService: NotificationService,
                                   wsClient: WSClient)(implicit ec: ExecutionContext, materializer: Materializer) {

  var currentIpAddress : Option[String] = None
  val cancellable = actorSystem.scheduler.schedule(0.seconds, 1.seconds)(runJob)

  /**
    * This job is run every 3 seconds and it's result
    * is pushed via web sockets to all subscribers.
    */
  private def runJob : Unit = {
    val notification = Notification("synology_host_finder", JsString("Hello World"))
    notificationService.pushNotification(notification)
  }

  private def checkService(host: String): Future[Boolean] = wsClient.url(s"http://${host}").get().map(_.status == 200)

}
