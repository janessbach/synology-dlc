package app.modules.synology.client

import mixins.NoApplication
import mockws.MockWS
import modules.synology.client.{ClientApi, ClientApiConfiguration}
import play.api.mvc.{Action, Results}

class ClientApiSpec extends NoApplication {

  trait Env {
    val SuccessResponse = s"""{ "esearchresult": { "idlist": [""] } }"""
    val ErrorResponse = """{ "malformed" : "noidea" }"""

    val clientApiConfiguration = mock[ClientApiConfiguration]

    val ws = MockWS {
      case ("GET", url) if url == "" => Action { Results.Ok(SuccessResponse).as("application/json") }
      case ("GET", url) if url == "" => Action { Results.Ok(ErrorResponse).as("application/json") }
    }
  }

  "PubMedSearchService" should {

    "be able to query the service and get the results" in new Env {

      val service = new ClientApi(ws, clientApiConfiguration)

      /*
      val futurePubMedModel: Future[PubMedModel] = service.queryWebservice("Automobil")

      futurePubMedModel.map { model =>
        model.results.length mustEqual 1
        val firstResult = model.results.head
        firstResult.title mustEqual "Experimental Assessment of NOx Emissions from 73 Euro 6 Diesel Passenger Cars."
      }
      */

    }

    "fail if the response of the external service does not conform protocol" in {

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

