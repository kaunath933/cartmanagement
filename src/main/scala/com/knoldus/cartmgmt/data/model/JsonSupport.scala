package com.knoldus.cartmgmt.data.model

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val UserDetailsFormat = jsonFormat7(UserDetails)
  implicit val UserAcoountFormat = jsonFormat3(UserAccount)
  implicit val UserCartFormat = jsonFormat4(UserCart)
}
