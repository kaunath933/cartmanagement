package com.knoldus.cartrepo

import com.knoldus.cartmgmt.config.DB
import com.knoldus.cartmgmt.data.model.{JsonSupport, UserDetails}
import com.knoldus.cartmgmt.data.Dao.UserDetailsRepositoryDaoImpl
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

import scala.concurrent.ExecutionContext.Implicits.global

class DetailsUserSpec extends FunSuite with ScalaFutures with JsonSupport {
  this: DB =>

  val testObj = new UserDetailsRepositoryDaoImpl with H2Impl

  implicit val defaultPatience = PatienceConfig(Span(5,Seconds), Span(500, Millis))

  test("Register a new user") {
    val testUser = UserDetails(2, "male", "testUser", "54321", "test@gmail.com", "test123", "")
    val result = testObj.registerUser(testUser)
    whenReady(result) { status =>
      assert(status === 1)
    }
  }

  test("Get Details of all users") {
    val response = testObj.all
    whenReady(response) { y =>
      assert(y === Vector(UserDetails(2, "male", "testUser", "54321", "test@gmail.com", "test123", "")))
    }
  }

  test("Authenticate an user") {
    val result = testObj.authentiacteUser(2)
    whenReady(result) { status =>
      assert(status === Vector(UserDetails(2, "male", "testUser", "54321", "test@gmail.com", "test123", "")))
    }
  }
}
