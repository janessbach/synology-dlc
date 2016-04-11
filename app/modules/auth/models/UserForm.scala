package modules.auth.models

import play.api.data._
import play.api.data.Forms._

case class UserForm(name: String, password: String)

object UserForm {
  val Username = "username"
  val Password = "password"

  val formMapping = Form(
    mapping(
      Username -> text,
      Password -> text
    )(UserForm.apply)(UserForm.unapply)
  )
}