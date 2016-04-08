package mixins.suites

import org.scalatest.mock.MockitoSugar

trait BaseMixin extends MockitoSugar {
  import scala.concurrent.ExecutionContext.Implicits.global
  implicit val ec = global
}