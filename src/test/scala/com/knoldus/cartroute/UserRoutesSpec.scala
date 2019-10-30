import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, MediaTypes}
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import akka.util.ByteString
import com.knoldus.cartmgmt.data.model.{JsonSupport, UserAccount, UserCart, UserDetails}
import com.knoldus.cartmgmt.routes.AllRoutes
import com.knoldus.cartmgmt.service.{UserAccountServiceImpl, UserCartServiceImpl, UserDetailsServiceImpl}
import org.mockito.Mockito._
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.mockito.MockitoSugar

import scala.concurrent.Future
import scala.concurrent.duration._

class UserRoutesSpec extends WordSpec with Matchers with ScalatestRouteTest with MockitoSugar with JsonSupport {

  implicit val timeout = RouteTestTimeout(3 seconds)
  val mockUserAccountService = mock[UserAccountServiceImpl]
  val mockCartService = mock[UserCartServiceImpl]
  val mockUserDetailsService = mock[UserDetailsServiceImpl]

  object TestObject extends AllRoutes {
    val userDetailsService = mockUserDetailsService
    val userCartService = mockCartService
    val userAccountService = mockUserAccountService
  }

  "The service" should {
    "fetch all employees stored in the Database" in {
      when(mockUserDetailsService.getAll).thenReturn(Future[Seq[UserDetails]](Seq(UserDetails(1, "male", "test12", "12345", "test12@gmail.com", "testX", "last"))))
      Get("/getAllUser") ~> TestObject.routes ~> check {
        status.isSuccess() shouldEqual true
        responseAs[Seq[UserDetails]] shouldEqual (Seq(UserDetails(1, "male", "test12", "12345", "test12@gmail.com", "testX", "last")))
      }
    }
  }

  "The service" should {
    "return user registered as response for a Post request to /registerUser" in {

      when(mockUserDetailsService.registerNewUser(UserDetails(1, "male", "John12", "12345", "test@gmail.com", "John", "Doe"))).thenReturn(Future[Int](1))

      val jsonRequest = ByteString(
        s"""
           |{
           |    "userId":1,
           |    "gender":"male",
           |    "userName":"John12",
           |    "password":"12345",
           |    "email":"test@gmail.com",
           |    "firstName":"John",
           |    "lastName":"Doe"
           |}
        """.stripMargin)

      val postRequest = HttpRequest(
        HttpMethods.POST,
        uri = "/registerUser",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest))

      postRequest ~> TestObject.routes ~> check {
        status.isSuccess() shouldEqual true
        responseAs[String] shouldEqual "User registered"
      }
    }
  }

  "The service" should {
    "return newItem is added as response for a Post request to /addItem" in {

      when(mockUserDetailsService.verifyUser(1)).thenReturn(Future[Seq[UserDetails]](Seq(UserDetails(1, "male", "John12", "12345", "test@gmail.com", "John", "Doe"))))

      when(mockCartService.verifyUIid(3, 1)).thenReturn(Future[Seq[UserCart]](Seq()))

      when(mockCartService.insertNewRow(UserCart(1, 3, 3, 600))).thenReturn(Future[Int](1))

      val jsonRequest = ByteString(
        s"""
           |{
           |    "userId":1,
           |    "itemId":3,
           |    "quantity":3,
           |    "price":600
           |}
          """.stripMargin)
      val postRequest = HttpRequest(
        HttpMethods.POST,
        uri = "/addItem",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest))

      postRequest ~> TestObject.routes ~> check {
        responseAs[String] shouldEqual "newItem is added"
      }
    }
  }

  "The service" should {
    "return item is updated as response for a Post request to /addItem" in {

      when(mockUserDetailsService.verifyUser(1)).thenReturn(Future[Seq[UserDetails]](Seq(UserDetails(2, "male", "kevin12", "xyz", "kev@gmail.com", "kevin", "john"))))

      when(mockCartService.verifyUIid(3, 1)).thenReturn(Future[Seq[UserCart]](Seq(UserCart(1,3,4,500))))

      when(mockCartService.insertQuantity(UserCart(1, 3, 4, 500))).thenReturn(Future[Int](1))

      val jsonRequest = ByteString(
        s"""
           |{
           |    "userId":1,
           |    "itemId":3,
           |    "quantity":4,
           |    "price":500
           |}
          """.stripMargin)
      val postRequest = HttpRequest(
        HttpMethods.POST,
        uri = "/addItem",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest))

      postRequest ~> TestObject.routes ~> check {
        responseAs[String] shouldEqual "item updated"
      }
    }
  }

  "The service" should {
    "fetch the total checkout price of an user" in {
      when(mockCartService.getAll(1)).thenReturn(Future[Vector[Float]](Vector(500)))
      Get("/checkOut/1") ~> TestObject.routes ~> check {
        status.isSuccess() shouldEqual true
        responseAs[Vector[Float]] shouldEqual (Vector(500))
      }
    }
  }

  "The service" should {
    "fetch the balance of a user from Database" in {
      when(mockUserAccountService.getAll).thenReturn(Future[Seq[UserAccount]](Seq(UserAccount(1, "test", 2000))))
      Get("/getAccountDetails") ~> TestObject.routes ~> check {
        status.isSuccess() shouldEqual true
        responseAs[Seq[UserAccount]] shouldEqual (Seq(UserAccount(1, "test", 2000)))
      }
    }
  }
}
