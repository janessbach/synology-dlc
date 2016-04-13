package modules.synology.services

import com.google.inject.Inject
import modules.core.auth.models.{LoginStatus, LogoutStatus, User}
import modules.synology.client.ClientApi

import scala.concurrent.Future

class SynologyService @Inject() (client : ClientApi) {

  def login(username: String, password: String) : Future[LoginStatus] = client.login(username, password)

  def logout(user: User) : Future[LogoutStatus] = client.logout(user.loginStatus)

}
