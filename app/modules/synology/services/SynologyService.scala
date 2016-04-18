package modules.synology.services

import com.google.inject.Inject
import modules.core.auth.models.{LoginStatus, LogoutStatus, User}
import modules.synology.client.SynologyClient
import modules.synology.models.downloads.{DownloadStatus, Downloads}

import scala.concurrent.Future

class SynologyService @Inject() (client : SynologyClient) {

  def addDownloads(user: User)(urls: List[String]): Future[DownloadStatus] =  client.addDownloads(user.loginStatus)(urls)

  def currentDownloads(user: User): Future[Downloads] = client.currentDownloads(user.loginStatus)

  def login(username: String, password: String) : Future[LoginStatus] = client.login(username, password)

  def logout(user: User) : Future[LogoutStatus] = client.logout(user.loginStatus)

}
