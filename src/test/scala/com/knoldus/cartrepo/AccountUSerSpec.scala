package com.knoldus.cartrepo

import com.knoldus.cartmgmt.data.model.UserAccount
import com.knoldus.cartmgmt.data.persistence.{AccountUser, DB}
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

import scala.concurrent.ExecutionContext.Implicits.global

class AccountUSerSpec extends FunSuite with ScalaFutures with AccountUser {
  this: DB =>

  val testObj = new UserAccountRepo with H2Impl

  implicit val defaultPatience: PatienceConfig = PatienceConfig(timeout = Span(5, Seconds), interval = Span(1000, Millis))

  test("Get Details of all users accounts") {
    val response = testObj.all
    whenReady(response) { y =>
      assert(y === Vector(UserAccount(1, "kaushik", 5000)))

    }

  }

}
