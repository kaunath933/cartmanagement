package com.knoldus.cartmgmt.services

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.knoldus.cartmgmt.data.persistence.{ AccountUser, CartUser, MySqlDB, DetailsUser }
import com.knoldus.cartmgmt.routes._

import scala.concurrent.ExecutionContext

trait HttpService extends DetailsUser  with AccountUser with CartUser {
  implicit lazy val system = ActorSystem()
  implicit lazy val materializer = ActorMaterializer()
  implicit lazy val ec: ExecutionContext = system.dispatcher

  lazy val userDetailsRepo: UserDetailsRepository = new UserDetailsRepository with MySqlDB
  lazy val userAccountRepo: UserAccountRepo = new UserAccountRepo with MySqlDB
  lazy val cartItemRrepo: UserCartRepo = new UserCartRepo with MySqlDB
  lazy val routes: Route = new AllRoutes(userDetailsRepo, userAccountRepo, cartItemRrepo).routes

}
