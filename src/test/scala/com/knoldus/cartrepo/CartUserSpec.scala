package com.knoldus.cartrepo

import com.knoldus.cartmgmt.config.DB
import com.knoldus.cartmgmt.data.Dao.UserCartRepoDaoImpl
import com.knoldus.cartmgmt.data.model.{JsonSupport, UserCart}
import org.scalatest.{AsyncFunSuite, FunSuite}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import scala.concurrent.ExecutionContext.Implicits.global


class CartUserSpec extends FunSuite with ScalaFutures with JsonSupport {
  this: DB =>

  val testObj = new UserCartRepoDaoImpl with H2Impl

  implicit val defaultPatience: PatienceConfig = PatienceConfig(timeout = Span(5, Seconds), interval = Span(1000, Millis))

  test("Adding a new row") {
    val testUser = UserCart(5, 3, 5, 600.0)
    val result = testObj.addNewRow(testUser)
    whenReady(result) { status =>
      assert(status === 1)
    }
  }

  test("Adding quantity to an existing column") {
    val testUser = UserCart(1, 2, 4, 500)
    val result = testObj.addQuantity(testUser)
    whenReady(result) { status =>
      assert(status === 1)
    }
  }

  test("Getting the total checkout price for an user"){
    val testUser = UserCart(1, 2, 4, 500)
    val result = testObj.all(1)
    whenReady(result) {status =>
      assert(status === Vector(2000))
    }
  }
}
