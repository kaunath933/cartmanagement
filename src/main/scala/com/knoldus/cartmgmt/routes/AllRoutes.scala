package com.knoldus.cartmgmt.routes

import akka.http.scaladsl.server.Directives.{path, _}
import akka.http.scaladsl.server.Route
import com.knoldus.cartmgmt.config.CartMgmtConfig
import com.knoldus.cartmgmt.data.model.{JsonSupport, UserCart, UserDetails}
import com.knoldus.cartmgmt.data.persistence._

import scala.concurrent.ExecutionContext
import scala.util.Success

class AllRoutes(repo: DetailsUser#UserDetailsRepository, repo3: AccountUser#UserAccountRepo, repo4: CartUser#UserCartRepo)(implicit ex: ExecutionContext)
  extends JsonSupport with CartMgmtConfig {

  val routes: Route = path("getAllUser") {
    get {
      complete(repo.all)
    }

  } ~
    path("registerUser") {
      post {
        entity(as[UserDetails]) { user =>
          val stored = repo.registerUser(user)
          onComplete(stored) {
            done => complete("User registered")
          }
        }
      }
    } ~
    pathPrefix("addItem") {
      post {
        entity(as[UserCart]) { item =>
          onComplete(repo.authentiacteUser(item.userId)) {
            case Success(user) if user.length != 0 => onComplete(repo4.checkUIid(item.itemId, item.userId)) {
              case Success(itemPresent) if itemPresent.length != 0 => onComplete(repo4.addQuantity(item)) { done =>
                complete("item updated")
              }

              case Success(newItem) => onComplete(repo4.addNewRow(item)) {
                done => complete("newItem is added")
              }

            }

            case Success(message) => complete("no user found")
          }

        }

      }

    } ~ path("checkOut" / IntNumber) { id =>
    get {
      complete("Your total price is")
      complete(repo4.all(id))
    }

  } ~
    path("getAccountDetails") {
      get {
        complete(repo3.all)
      }

    }

}
