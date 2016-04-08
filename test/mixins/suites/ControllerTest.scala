package mixins.suites

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}

/**
  * ApplicationSpec
  *
  * "HomeController" should {
  *  "render the index page" in {
  *  val home = route(app, FakeRequest(GET, "/")).get
  *  status(home) mustBe OK
  *  contentType(home) mustBe Some("text/html")
  *  contentAsString(home) must include ("Your new application is ready.")
  *  }
  * }
 */
trait ControllerTest extends PlaySpec with BaseMixin with OneAppPerTest
