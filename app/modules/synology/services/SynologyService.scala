package modules.synology.services

import com.google.inject.Inject
import modules.core.auth.models.{LoginStatus, LogoutStatus, User}
import modules.synology.client.{ClientApi, DownloadApi}
import modules.synology.models.downloads.{DownloadStatus, Downloads}

import scala.concurrent.Future

class SynologyService @Inject() (client : ClientApi,
                                 downloadApi: DownloadApi) {

  def login(username: String, password: String) : Future[LoginStatus] = client.login(username, password)

  def logout(user: User) : Future[LogoutStatus] = client.logout(user.loginStatus)

  def downloads(user: User): Future[Downloads] = downloadApi.listDownloads(user.loginStatus)

  def download(user: User)(urls: List[String]): Future[DownloadStatus] =  downloadApi.download(user.loginStatus)(urls)

}
