package mixins

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}

trait OneApplicationPerTest extends PlaySpec with BaseMixin with OneAppPerTest
