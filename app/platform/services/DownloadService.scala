package platform.services

import com.google.inject.Inject
import modules.core.auth.models.User
import modules.synology.services.SynologyService
import platform.models.Download

import scala.concurrent.{ExecutionContext, Future}

class DownloadService @Inject()(synologyService : SynologyService)(implicit ec: ExecutionContext)  {

  def downloads(user: User): Future[Option[List[Download]]] = synologyService.currentDownloads(user)
    .map { downloads =>
      if (downloads.success)
        Option(downloads.files.map(file => Download(id = file.id, size = file.size, status = file.status, title = file.title)))
      else
       None
    }

  def download(user: User)(urls: List[String]): Future[Boolean] = synologyService.addDownloads(user)(urls).map(_.success)
}


