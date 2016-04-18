package modules.synology.services

import com.google.inject.Inject
import modules.core.auth.models.{LoginStatus, LogoutStatus, User}
import modules.synology.client.SynologyClient
import modules.synology.models.downloads.{DownloadStatus, Downloads}

import scala.concurrent.Future

class SynologyService @Inject() (client : SynologyClient) {

  def login(username: String, password: String) : Future[LoginStatus] = client.login(username, password)

  def logout(user: User) : Future[LogoutStatus] = client.logout(user.loginStatus)

  def downloads(user: User): Future[Downloads] = client.listDownloads(user.loginStatus)

  def download(user: User)(urls: List[String]): Future[DownloadStatus] =  client.download(user.loginStatus)(urls)

}
