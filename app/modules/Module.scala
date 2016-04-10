package modules

import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import modules.dlc.services.{DefaultFileService, RemoteFileService, ShareOnlineFileService, UploadedToFileService}

class Module extends AbstractModule {

  override def configure(): Unit = {

    val remoteFileHandlers = Multibinder.newSetBinder(binder, classOf[RemoteFileService])
    remoteFileHandlers.addBinding().to(classOf[ShareOnlineFileService])
    remoteFileHandlers.addBinding().to(classOf[UploadedToFileService])

  }

}





