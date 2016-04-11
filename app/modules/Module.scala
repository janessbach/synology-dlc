package modules

import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import modules.auth.services.AuthService
import modules.dlc.services.{RemoteFileService, ShareOnlineFileService}
import services.SynologyAuthService

class Module extends AbstractModule {

  override def configure(): Unit = {

    val remoteFileHandlers = Multibinder.newSetBinder(binder, classOf[RemoteFileService])
    remoteFileHandlers.addBinding().to(classOf[ShareOnlineFileService])

    binder.bind(classOf[AuthService]).to(classOf[SynologyAuthService])
  }

}





