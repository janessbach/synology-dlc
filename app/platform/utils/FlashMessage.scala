package platform.utils

import modules.core.controllers.RequestContext
import play.api.mvc.Result

abstract class LogLevel(sessionKey : String)
case object MessageInfo extends LogLevel(FlashMessage.InfoMessage)
case object MessageSuccess extends LogLevel(FlashMessage.SuccessMessage)
case object MessageError extends LogLevel(FlashMessage.ErrorMessage)

abstract class FlashMessage(logLevel : LogLevel, message : String) {
  def userMessage = message
  def cssClass : String
}
case class InfoMessage(message : String) extends FlashMessage(logLevel = MessageInfo, message) {
  def cssClass = "notice"
}
case class ErrorMessage(message : String) extends FlashMessage(logLevel = MessageError, message) {
  def cssClass = "error"
}
case class SuccessMessage(message : String) extends FlashMessage(logLevel = MessageSuccess, message) {
  def cssClass = "success"
}

object FlashMessage {

  val ErrorMessage = "flashing.error"
  val SuccessMessage = "flashing.success"
  val InfoMessage = "flashing.info"

  def createFlashing(key : String, message: String) : FlashMessage = {
    key match {
      case ErrorMessage => new ErrorMessage(message)
      case SuccessMessage => new SuccessMessage(message)
      case InfoMessage => new InfoMessage(message)
      case _ => new InfoMessage(message)
    }
  }

  def getMessages[A](requestContext: RequestContext[A]) : Option[List[FlashMessage]] = {
    val items : Map[String, String] = requestContext.request.flash.data
    Option(items.map {
      case (key : String, message: String) => createFlashing(key, message)
    }.toList)
  }

  def info(result : Result, message : String)     = result.flashing(InfoMessage -> message)
  def error(result : Result, message : String)    = result.flashing(ErrorMessage -> message)
  def success(result : Result, message : String)  = result.flashing(SuccessMessage -> message)

}
