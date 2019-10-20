package com.knoldus.cartmgmt.data.persistence

import com.knoldus.cartmgmt.data.model._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext

trait DetailsUser {

  class UserDetailsTable(tag: Tag) extends Table[UserDetails](tag, "USER_DETAILS") {

    def userId = column[Int]("userId", O.PrimaryKey)

    def gender = column[String]("gender")

    def userName = column[String]("userName")

    def password = column[String]("password")

    def email = column[String]("email")

    def firstName = column[String]("firstName")

    def lastName = column[String]("lastName")

    def * = (userId, gender, userName, password, email, firstName, lastName).<>(UserDetails.tupled, UserDetails.unapply)

  }

  val userDetailsRef = TableQuery[UserDetailsTable]

  class UserDetailsRepository(implicit ec: ExecutionContext) {
    this: DB =>
    def all = db.run {
      userDetailsRef.result
    }

    def registerUser(user: UserDetails) = db.run {
      userDetailsRef += user
    }

    def authentiacteUser(uid: Int) = db.run {
            userDetailsRef.filter(x => x.userId === uid).result
       }

  }

}

