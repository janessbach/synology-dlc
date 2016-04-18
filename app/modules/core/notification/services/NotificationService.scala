package modules.core.notification.services

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Flow, Keep, Sink, Source}
import akka.stream.{Materializer, OverflowStrategy}
import com.google.inject.{Inject, Singleton}
import modules.core.notification.models.{Notification, UserActor}
import play.api.libs.json.{JsValue, Json}

import scala.concurrent.ExecutionContext

@Singleton
class NotificationService @Inject()(implicit ec: ExecutionContext, actorSystem: ActorSystem, materializer: Materializer) {

  private val (outActor, publisher) = Source.actorRef[JsValue](99, OverflowStrategy.dropNew)
    .toMat(Sink.asPublisher(true))(Keep.both).run()

  def pushNotification(notification: Notification) = outActor ! Json.toJson(notification)

  def flow = Flow.fromSinkAndSource(
    sink = Sink.actorRef(actorSystem.actorOf(UserActor.props(outActor)), akka.actor.Status.Success(())),
    source = Source.fromPublisher(publisher)
  )

}



