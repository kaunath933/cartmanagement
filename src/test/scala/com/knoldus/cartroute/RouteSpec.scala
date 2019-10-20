//import akka.http.scaladsl.server.Route
//import akka.http.scaladsl.testkit.ScalatestRouteTest
//import com.knoldus.cartmgmt.data.model.{JsonSupport, UserDetails}
//import com.knoldus.cartmgmt.data.persistence.{AccountUser, Cart, CheckOutCart, ItemDetails, MySqlDB, UserDetailsComponent}
//import com.knoldus.cartmgmt.routes.AllRoutes
//import com.knoldus.inventoryrepo.H2Impl
//import org.scalatest.{Matchers, WordSpec}
//import org.scalatest.mock.MockitoSugar
//
//
//class UserRoutesSpec extends WordSpec with Matchers with ScalatestRouteTest with UserDetailsComponent with ItemDetails with AccountUser with CheckOutCart with Cart with MockitoSugar with JsonSupport{
//
//  lazy val testUserDetailsRepo: UserDetailsRepository = new UserDetailsRepository with H2Impl
//  lazy val testItemDetailsRepo: ItemDetailsRepo = new ItemDetailsRepo with H2Impl
//  lazy val testUserAccountRepo: UserAccountRepo = new UserAccountRepo with H2Impl
//  lazy val testCartItemRrepo: UserCartRepo = new UserCartRepo with H2Impl
//  lazy val testCheckOutCartRepo: CheckOutCartRepo = new CheckOutCartRepo with H2Impl
//  lazy val testRoutes: Route = new AllRoutes(testUserDetailsRepo, testItemDetailsRepo, testUserAccountRepo, testCartItemRrepo, testCheckOutCartRepo).routes
//
//  //  val mockRepo = mock[TestRepo]
//  //  implicit val masterUserListFormat = jsonFormat1(Seq(MasterUser))
//  "The service" should {
//    "return a list of all users available in the database" in {
//
//      //      when(mockRepo.all).thenAnswer("Test")
//      //implicit val MasterUserFormat =
//      Get("/getAllUser") ~> testRoutes ~> check {
//        responseAs[Vector[UserDetails]] shouldEqual (Vector(UserDetails("", "johndoe@gmail.com", "John Doe", 32, false), MasterUser("9622142113", "james@gmail.com", "James", 50, true)))
//      }
//    }
//  }
//  }