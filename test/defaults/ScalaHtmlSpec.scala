package defaults

import org.specs2.mutable.Specification
import play.api.test.WithApplication
import utils.English

class ScalaHtmlSpec extends Specification with ScalaHtmlSpecMock {

  "Snippet" should {
    "be rendered properly" in new WithApplication() with English {
      // pubmedSnippet("PubMed", pubMedModel).compressed === Html(expectedHtml).compressed
    }

    "render nothing if ..." in new WithApplication() with English {
      // pubmedSnippet("PubMed", PubMedModel(Nil)).body.trim must be empty
    }
  }

}

trait ScalaHtmlSpecMock {}