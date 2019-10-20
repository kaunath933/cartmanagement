package com.knoldus.cartmgmt.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.{ ActorMaterializer, Materializer }

import scala.io.StdIn
import akka.http.scaladsl.server.Route
import com.knoldus.cartmgmt.config.CartMgmtConfig
import akka.http.scaladsl.Http
import com.knoldus.cartmgmt.services.HttpService

import scala.concurrent.ExecutionContext

trait HttpServer extends CartMgmtConfig with HttpService {
  implicit def system: ActorSystem
  implicit def materializer: Materializer

  val routes: Route
  Http().bindAndHandle(routes, httpHost, httpPort)
  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine()
}

