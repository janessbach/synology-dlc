package app.controllers

import mixins.suites.OneApplicationPerTest
import play.api.test.FakeRequest

import play.api.test.Helpers._

class ApiControllerSpec extends OneApplicationPerTest {

  "ApiController" should {
     "render an valid json response for an valid dlc file" in {
       val home = route(app, FakeRequest(GET, "/")).get

       status(home) mustBe OK
       contentType(home) mustBe Some("text/html")
       contentAsString(home) must include ("Your new application is ready.")
     }
  }

}
