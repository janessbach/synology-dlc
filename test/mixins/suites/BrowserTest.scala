package mixins.suites

import org.scalatestplus.play.{HtmlUnitFactory, OneBrowserPerTest, OneServerPerTest, PlaySpec}

/**
  ** IntegrationSpec
  *
  * "Application" should {

    *"work from within a browser" in {

      *go to ("http://localhost:" + port)

      *pageSource must include ("Your new application is ready.")
    *}
  *}
 */
trait BrowserTest extends PlaySpec with BaseMixin with OneServerPerTest with OneBrowserPerTest with HtmlUnitFactory
