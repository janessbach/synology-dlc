import platform.utils.{Error, Message, Success}

package object controllers {
  object LoginSuccessful extends Message("login.successful.title", "login.successful.message")(Success)
  object LoginError extends Message("login.error.title", "login.error.message")(Error)
}
