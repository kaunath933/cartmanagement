package com.knoldus.cartrepo

import com.knoldus.cartmgmt.data.model.UserDetails
import com.knoldus.cartmgmt.data.persistence.{DB, DetailsUser}
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

import scala.concurrent.ExecutionContext.Implicits.global

class DetailsUserSpec extends FunSuite with ScalaFutures with DetailsUser {
  this: DB =>

  val testObj = new UserDetailsRepository with H2Impl

  implicit val defaultPatience: PatienceConfig = PatienceConfig(timeout = Span(5, Seconds), interval = Span(1000, Millis))

  test("Get Details of all users") {
    val response = testObj.all
    whenReady(response) { y =>
      assert(y === Vector(UserDetails(1, "male", "kaushik123", "12345", "abc@gmail.com", "kaushik", "Nath")))

    }

  }

  test("Register a new user") {
    val testUser = UserDetails(2, "male", "testUser", "54321", "test@gmail.com", "test123", "")
    val result = testObj.registerUser(testUser)
    whenReady(result) { status =>
      assert(status === (1, 1))
    }
  }

  test("Authenticate an user") {
    val result = testObj.authentiacteUser(2)
    whenReady(result) { status =>
      assert(status === Vector(true))
    }
  }
}
