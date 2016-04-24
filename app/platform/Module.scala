package platform

import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import modules.core.auth.services.AuthService
import modules.dlc.services.{RemoteFileService, ShareOnlineFileService}
import platform.jobs.SynologyHostFinder
import platform.services.PlatformAuthService

class Module extends AbstractModule {

  override def configure(): Unit = {
    binder.bind(classOf[SynologyHostFinder]).asEagerSingleton()

    val remoteFileHandlers = Multibinder.newSetBinder(binder, classOf[RemoteFileService])
    remoteFileHandlers.addBinding().to(classOf[ShareOnlineFileService])

    binder.bind(classOf[AuthService]).to(classOf[PlatformAuthService])
  }

}





