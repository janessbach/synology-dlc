package modules.core

import com.google.inject.AbstractModule

class CoreModule extends AbstractModule {

  override def configure(): Unit = {
    //bind(classOf[ConfigProvider]).to(classOf[LocalFileSourceProvider])
    //bind(classOf[UserService]).to(classOf[SqlUserService])
  }

}





