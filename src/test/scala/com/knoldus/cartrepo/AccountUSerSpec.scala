package com.knoldus.cartrepo

import com.knoldus.cartmgmt.config.DB
import com.knoldus.cartmgmt.data.model.{JsonSupport, UserAccount}
import com.knoldus.cartmgmt.data.Dao.{UserAccountRepoDaoImpl}
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

import scala.concurrent.ExecutionContext.Implicits.global

class AccountUSerSpec extends FunSuite with ScalaFutures with JsonSupport {
  this: DB =>

  val testObj = new UserAccountRepoDaoImpl with H2Impl

  implicit val defaultPatience: PatienceConfig = PatienceConfig(timeout = Span(5, Seconds), interval = Span(1000, Millis))

  test("Get Details of all users accounts") {
    val response = testObj.all
    whenReady(response) { y =>
      assert(y === Seq(UserAccount(1,"testX",2000)))

    }
  }
}
