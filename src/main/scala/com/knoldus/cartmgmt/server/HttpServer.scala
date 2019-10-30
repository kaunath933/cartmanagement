package com.knoldus.cartmgmt.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.{ActorMaterializer, Materializer}
import com.knoldus.cartmgmt.config.CartMgmtConfig
import com.knoldus.cartmgmt.routes.AllRoutesImpl

import scala.concurrent.ExecutionContext
import scala.io.StdIn

class HttpServer(implicit val system: ActorSystem, implicit val materializer: Materializer)
  extends AllRoutesImpl with CartMgmtConfig {

  def startServer(address: String, port: Int) {
    implicit val executor: ExecutionContext = system.dispatcher

    val bindingFuture = Http().bindAndHandle(routes, httpHost, httpPort)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}

object HttpServer {

  def main(args: Array[String]): Unit = {
    implicit val actorSystem = ActorSystem("rest-server")
    implicit val materializer = ActorMaterializer()

    val server = new HttpServer()
    server.startServer("localhost", 8080)
  }
}
