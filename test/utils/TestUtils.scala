package utils

import org.jsoup.nodes.Element
import play.twirl.api.Html

trait TestUtils {
  implicit class TestHtmlFragmentFromTwirl(val html: Html) extends TestHtml {
    def text: String = html.body
  }
  implicit class TestHtmlFragmentFromJsoup(val element: Element) extends TestHtml {
    def text: String = element.outerHtml
  }
}



