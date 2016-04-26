package modules.synology.services

import com.google.inject.Inject
import modules.core.auth.models.{LoginStatus, LogoutStatus, User}
import modules.core.controllers.RequestContext
import modules.dlc.models.RemoteFile
import modules.synology.client.SynologyClient
import modules.synology.models.downloads.Downloads

import scala.concurrent.{ExecutionContext, Future}

class SynologyService @Inject() (client : SynologyClient)(implicit ec: ExecutionContext) {

  def addDownloads[A](files: List[RemoteFile])(implicit context : RequestContext[A]): Future[Boolean] = {
    /**
      * Due to synology api limits, where limiting the files to packets of size 50.
      */
    def processFiles(files: List[RemoteFile]): Future[Boolean] = client
        .addDownloads(context.user.loginStatus)(files.map(_.url))
        .map(_.success)

    Future.sequence(files.grouped(50).map(processFiles).toList).map(_.forall(_ => true))
  }

  def currentDownloads(user: User): Future[Downloads] = client
    .currentDownloads(user.loginStatus)

  def login(username: String, password: String) : Future[LoginStatus] =
    client.login(username, password)

  def logout(user: User) : Future[LogoutStatus] =
    client.logout(user.loginStatus)

}
