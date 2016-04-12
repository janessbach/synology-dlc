package modules.synology.services

import com.google.inject.Inject
import modules.auth.models.LoginStatus
import modules.synology.client.ClientApi

import scala.concurrent.Future

class SynologyService @Inject() (client : ClientApi) {

  def login(username: String, password: String) : Future[LoginStatus] = client.login(username, password)

}
