package com.knoldus.cartmgmt.routes

import akka.http.scaladsl.server.Directives.{ path, _ }
import akka.http.scaladsl.server.Route
import com.knoldus.cartmgmt.config.CartMgmtConfig
import com.knoldus.cartmgmt.data.model.{ JsonSupport, UserCart, UserDetails }
import com.knoldus.cartmgmt.service._

import scala.util.{ Failure, Success }

trait AllRoutes extends JsonSupport with CartMgmtConfig {

  val userDetailsService: UserDetailsService
  val userCartService: UserCartService
  val userAccountService: UserAccountService

  val routes: Route = path("getAllUser") {
    get {
      complete(userDetailsService.getAll)
    }

  } ~
    path("registerUser") {
      post {
        entity(as[UserDetails]) { user =>
          val stored = userDetailsService.registerNewUser(user)
          onComplete(stored) {
            done => complete("User registered")
          }
        }
      }
    } ~
    pathPrefix("addItem") {
      post {
        entity(as[UserCart]) { item =>
          onComplete(userDetailsService.verifyUser(item.userId)) {
            case Success(user) if user.length != 0 => onComplete(userCartService.verifyUIid(item.itemId, item.userId)) {
              case Success(itemPresent) if itemPresent.length != 0 => onComplete(userCartService.insertQuantity(item)) { done =>
                complete("item updated")
              }

              case Success(newItem) if newItem.isEmpty => onComplete(userCartService.insertNewRow(item)) {
                done => complete("newItem is added")
              }

              case Failure(ex) => complete(ex.getMessage)

            }

            case Success(message) => complete("no user found")
            case Failure(ex) => complete(ex.getMessage)
          }

        }

      }

    } ~
    path("checkOut" / IntNumber) { id =>
      get {

        complete(userCartService.getAll(id))
      }

    } ~
    path("getAccountDetails") {
      get {
        complete(userAccountService.getAll)
      }

    }

}

class AllRoutesImpl extends AllRoutes {
  val userDetailsService: UserDetailsService = UserDetailsServiceImpl
  val userCartService = UserCartServiceImpl
  val userAccountService = UserAccountServiceImpl
}
