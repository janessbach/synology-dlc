package platform.models

import platform.config.ConfigurationService
import play.api.data.Form
import play.api.data.Forms._

case class UserForm(ip: String, port : Int, name: String, password: String)

object UserForm {
  val Ip = "ip"
  val Username = "username"
  val Password = "password"
  val Port = "port"

  def fromConfiguration(service : ConfigurationService) : Form[UserForm] =
    UserForm.formMapping.bind(Map(
      UserForm.Ip -> service.hostIp,
      UserForm.Port -> service.hostPort.toString
    ))

  val formMapping = Form(
    mapping(
      Ip -> text,
      Port -> number,
      Username -> text,
      Password -> text
    )(UserForm.apply)(UserForm.unapply)
  )
}
