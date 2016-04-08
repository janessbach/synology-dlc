package utils

import play.api.Environment
import play.api.i18n.{DefaultLangs, DefaultMessagesApi, Lang, Messages}
import play.api.test.WithApplication

sealed trait WithLanguage {

  self: WithApplication =>

  def currentLanguage: Lang

  implicit def messages: play.api.i18n.Messages = {
    val api = new DefaultMessagesApi(Environment.simple(), app.configuration, new DefaultLangs(app.configuration))
    Messages.apply(currentLanguage, api)
  }

}

trait German extends WithLanguage {

  self: WithApplication =>

  override def currentLanguage: Lang = Lang("de")
}

trait English extends WithLanguage {

  self: WithApplication =>

  override def currentLanguage: Lang = Lang("en")
}

trait Dutch extends WithLanguage {

  self: WithApplication =>

  override def currentLanguage: Lang = Lang("nl")
}