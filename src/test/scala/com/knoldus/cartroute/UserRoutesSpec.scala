import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, MediaTypes}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import akka.util.ByteString
import com.knoldus.cartmgmt.data.model.{JsonSupport, UserDetails}
import com.knoldus.cartmgmt.data.persistence.{AccountUser, CartUser, DetailsUser}
import com.knoldus.cartmgmt.routes.AllRoutes
import com.knoldus.cartrepo.H2Impl
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.duration._

class UserRoutesSpec extends WordSpec with Matchers with ScalatestRouteTest with DetailsUser with AccountUser with CartUser with JsonSupport {

  lazy val testUserDetailsRepo: UserDetailsRepository = new UserDetailsRepository with H2Impl
  lazy val testUserAccountRepo: UserAccountRepo = new UserAccountRepo with H2Impl
  lazy val testCartItemRrepo: UserCartRepo = new UserCartRepo with H2Impl
  lazy val testRoutes: Route = new AllRoutes(testUserDetailsRepo, testUserAccountRepo, testCartItemRrepo).routes
  implicit val timeout = RouteTestTimeout(3 seconds)

  "The service" should {
    "return the list of all available users using GET request" in {

      Get("/getAllUser") ~> testRoutes ~> check {
        responseAs[Vector[UserDetails]] shouldEqual (Vector(UserDetails(1, "male", "kaushik123", "12345", "abc@gmail.com", "kaushik", "Nath")))
      }
    }

    "return user registered as response for a Post request to /registerUser" in {

      val jsonRequest = ByteString(
        s"""
           |{
           |    "userId": 1,
           |    "gender":"male",
           |    "userName": "John12",
           |    "password": "12345",
           |    "email": "test@gmail.com",
           |    "firstName": "John",
           |    "lastName": "Doe"
           |}
        """.stripMargin)

      val postRequest = HttpRequest(
        HttpMethods.POST,
        uri = "/registerUser",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest))

      postRequest ~> testRoutes ~> check {
        status.isSuccess() shouldEqual true
        responseAs[String] shouldEqual "User registered"
      }

    }
    "return newItem is added as response for a Post request to /addItem" in {

      val jsonRequest = ByteString(
        s"""
           |{
           |    "userId": 2,
           |    "itemId":3,newItem is added
           |    "quantity": 3,
           |    "price": 600
           |}
        """.stripMargin)
      val postRequest = HttpRequest(
        HttpMethods.POST,
        uri = "/addItem",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest))

      postRequest ~> testRoutes ~> check {
        status.isSuccess() shouldEqual true
        responseAs[String] shouldEqual "newItem is added"

      }

    }
    "return no User found as response for a Post request to /addItem" in {

      val jsonRequest = ByteString(
        s"""
           |{
           |    "userId": 1,
           |    "itemId":2,
           |    "quantity": 2,
           |    "price": 500
           |}
        """.stripMargin)
      val postRequest = HttpRequest(
        HttpMethods.POST,
        uri = "/addItem",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest))

      postRequest ~> testRoutes ~> check {
        status.isSuccess() shouldEqual true
        responseAs[String] shouldEqual "no user found"

      }

    }

    "return the total price after checkout for a user using GET request" in {

      Get("/checkOut/1") ~> testRoutes ~> check {
        responseAs[Vector[UserDetails]] shouldEqual (Vector(1000))
      }
    }

  }
}
