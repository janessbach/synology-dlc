package modules.core.authentication.models

import play.api.data.Form
import play.api.data.Forms._

case class UserData(email: String, password : String)

object UserData {
  val userNameField = "email"
  val userPasswordField = "password"
  val userForm : Form[UserData] = Form(mapping(userNameField -> email, userPasswordField -> nonEmptyText)(UserData.apply)(UserData.unapply))
}