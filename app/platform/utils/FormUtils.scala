package platform.utils

import play.api.data.Form

object FormUtils {

  def value[A](key : String)(form: Option[Form[A]]): Option[String] =
    form.flatMap(_.data.get(key))

}