package modules.core.notification.models

import akka.actor.{Actor, ActorRef, Props}
import play.api.libs.json.JsValue

class UserActor(out: ActorRef) extends Actor {
  def receive = {
    // receives messages from client browser here, out is actor that will send messages back to client(s)
    case msg: JsValue => out ! msg
  }
}

object UserActor {
  def props(out: ActorRef) = Props(new UserActor(out))
}