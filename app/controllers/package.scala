import modules.core.message.models.Message

package object controllers {

  private val Success = "success"
  private val Error = "error"

  object LoginSuccess extends Message("login.successful.title", "login.successful.message", Success)
  object LoginError extends Message("login.error.title", "login.error.message", Error)

}
