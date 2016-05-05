package controllers

import javax.inject.{Inject, Singleton}

import com.typesafe.config.ConfigValueFactory
import modules.core.controllers.CoreController
import platform.config.{ConfigurationService, Constants}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, AnyContent}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SettingsController @Inject()(configuration: ConfigurationService)
                                  (implicit exec: ExecutionContext) extends CoreController with Constants {

  def settings = BaseAction { implicit context =>
    val defaultValues = SynologySettingsForm.fromConfiguration(configuration)
    Future.successful(Ok(views.html.platform.settings(Option(defaultValues))))
  }

  def synologySettings: Action[AnyContent] = BaseAction { implicit context =>
    SynologySettingsForm.formMapping.bindFromRequest.fold(
      formWithErrors => {
        Future.successful(BadRequest(views.html.platform.settings(Some(formWithErrors))))
      },
      settingsData => {
        configuration.set(ConfigHostName, ConfigValueFactory fromAnyRef settingsData.ipAddress)
        configuration.set(ConfigHostPort, ConfigValueFactory fromAnyRef settingsData.port)

        Future.successful(
          redirect(controllers.routes.SettingsController.settings())
            .flashing()
        ) // settings updated
      }
    )
  }
}

case class SynologySettingsForm(ipAddress: String, port: Int)

object SynologySettingsForm {
  val Ip = "ip"
  val Port = "port"

  def fromConfiguration(service : ConfigurationService) : Form[SynologySettingsForm] =
    SynologySettingsForm.formMapping.bind(
      Map(
        SynologySettingsForm.Ip -> service.hostIp,
        SynologySettingsForm.Port -> service.hostPort.toString
      )
    )

  val formMapping = Form(
    mapping(
      Ip -> text,
      Port -> number
    )(SynologySettingsForm.apply)(SynologySettingsForm.unapply)
  )
}
