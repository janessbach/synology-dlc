package modules.core.authentication.models

sealed trait Role
case object Admin extends Role
case object Owner extends Role
case object Normal extends Role

object RoleUtils {
  def stringAsRole(role: String): Role = role match {
    case "admin" => Admin
    case "owner" => Owner
    case "user" => Normal
    case _ => Normal
  }
  def roleAsString(role: Role): String = role match {
    case Admin => "admin"
    case Owner => "owner"
    case Normal => "user"
    case _ => "user"
  }
}

case class User(id : Int, username : String, password : String, role : Role)
