package mixins

import org.scalatestplus.play.{HtmlUnitFactory, OneBrowserPerTest, OneServerPerTest, PlaySpec}

trait BrowserTest extends PlaySpec with BaseMixin with OneServerPerTest with OneBrowserPerTest with HtmlUnitFactory
