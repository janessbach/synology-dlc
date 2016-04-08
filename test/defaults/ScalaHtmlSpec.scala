package defaults

import mixins.suites.OneApplicationPerTest

class ScalaHtmlSpec extends OneApplicationPerTest {

  "Snippet" should {
    "be rendered properly" in {
      // pubmedSnippet("PubMed", pubMedModel).compressed === Html(expectedHtml).compressed
    }

    "render nothing if ..." in {
      // pubmedSnippet("PubMed", PubMedModel(Nil)).body.trim must be empty
    }
  }

}

trait ScalaHtmlSpecMock {}