package app.modules.synology.client

import mixins.suites.WSClientTest
import mockws.MockWS
import play.api.mvc.{Action, Results}

import play.api.test.WithApplication

class ClientApiSpec extends WSClientTest with ClientApiSpecMock {

  "PubMedSearchService" should {

    "be able to query the service and get the results" in new WithApplication() {

      /*
      val service = new Sy(ws)

      val futurePubMedModel: Future[PubMedModel] = service.queryWebservice("Automobil")

      futurePubMedModel.map { model =>
        model.results.length mustEqual 1
        val firstResult = model.results.head
        firstResult.title mustEqual "Experimental Assessment of NOx Emissions from 73 Euro 6 Diesel Passenger Cars."
      }

      */
    }

    "fail if the response of the external service does not conform protocol" in new WithApplication() {

      /*
      val service = new PubMedSearchService(ws)

      val futurePubMedModel: Future[PubMedModel] = service.queryWebservice("Error")

      futurePubMedModel.failed.map { e =>
        e === json.JsResultException
      }
      */
    }

  }

}

trait ClientApiSpecMock {
  val DocumentId = 26580818

  val SuccessIdSetResponse = s"""{ "esearchresult": { "idlist": ["$DocumentId"] } }"""
  val ErrorIdSetResponse = """{ "malformed" : "noidea" }"""
  val SuccessMetaDataResult = s"""{
                                  |  "result" : {
                                  |    "$DocumentId" : {
                                  |      "uid" : "$DocumentId",
                                  |      "pubdate" : "2015 Dec 15",
                                  |      "epubdate" : "2015 Dec 1",
                                  |      "source" : "Environ Sci Technol",
                                  |      "title" : "Experimental Assessment of NOx Emissions from 73 Euro 6 Diesel Passenger Cars."
                                  |    }
                                  |  }
                                  |}""".stripMargin

  val successIdSet = "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&retmode=json&term=Automobil"
  val errorIdSet = "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&retmode=json&term=Error"
  val successMetaData = "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esummary.fcgi?db=pubmed&retmode=json&rettype=abstract&id=26580818"

  val ws = MockWS {
    case ("GET", url) if url == successIdSet => Action { Results.Ok(SuccessIdSetResponse).as("application/json") }
    case ("GET", url) if url == successMetaData => Action { Results.Ok(SuccessMetaDataResult).as("application/json") }
    case ("GET", url) if url == errorIdSet => Action { Results.Ok(ErrorIdSetResponse).as("application/json") }
  }
}