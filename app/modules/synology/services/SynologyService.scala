package modules.synology.services

import com.google.inject.Inject
import modules.core.auth.models.{LoginStatus, LogoutStatus, User}
import modules.core.controllers.RequestContext
import modules.dlc.models.RemoteFile
import modules.synology.client.SynologyClient
import modules.synology.models.downloads.{DownloadStatus, Downloads}
import platform.models.Download

import scala.concurrent.{ExecutionContext, Future}

class SynologyService @Inject() (client : SynologyClient)(implicit ec: ExecutionContext) {

  def addDownloads[A](files: List[RemoteFile])(implicit context : RequestContext[A]): Future[Boolean] = client
    .addDownloads(context.user.loginStatus)(files.map(_.url))
    .map(_.success)

  // RETHINK ABOUT THIS DATA TYPE
  def currentDownloads(user: User): Future[Option[List[Download]]] = client
    .currentDownloads(user.loginStatus)
    .map { downloads =>
      if (downloads.success)
        Option(downloads.files.map(file => Download(id = file.id, size = file.size, status = file.status, title = file.title)))
      else
        None
    }

  def login(username: String, password: String) : Future[LoginStatus] =
    client.login(username, password)

  def logout(user: User) : Future[LogoutStatus] =
    client.logout(user.loginStatus)

}
