package com.knoldus.cartrepo

import com.knoldus.cartmgmt.data.model.UserCart
import com.knoldus.cartmgmt.data.persistence.{CartUser, DB}
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

import scala.concurrent.ExecutionContext.Implicits.global

class CartUserSpec extends FunSuite with ScalaFutures with CartUser {
  this: DB =>

  val testObj = new UserCartRepo with H2Impl

  implicit val defaultPatience: PatienceConfig = PatienceConfig(timeout = Span(5, Seconds), interval = Span(1000, Millis))

  test("Get Details of the total price of a user") {
    val response = testObj.all(1)
    whenReady(response) { y =>
      assert(y === Vector(UserCart(1, 2, 4, 500)))
    }
  }

  test("Adding quantity to an existing column") {
    val testUser = UserCart(1, 2, 4, 500)
    val result = testObj.addQuantity(testUser)
    whenReady(result) { status =>
      assert(status === Vector(UserCart(1, 2, 3, 200)))
    }
  }

  test("Adding a new row") {
    val testUser = UserCart(2, 3, 5, 600)

    val result = testObj.addNewRow(testUser)
    whenReady(result) { status =>
      assert(status === Vector(UserCart(2, 3, 5, 600)))
    }
  }
}
