package com.knoldus.cartmgmt.data.persistence

import com.knoldus.cartmgmt.data.model._
import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

import scala.concurrent.{ExecutionContext, Future}

trait DetailsUser {

  class UserDetailsTable(tag: Tag) extends Table[UserDetails](tag, "USER_DETAILS") {

    def userId: Rep[Int] = column[Int]("userId", O.PrimaryKey)

    def gender: Rep[String] = column[String]("gender")

    def userName: Rep[String] = column[String]("userName")

    def password: Rep[String] = column[String]("password")

    def email: Rep[String] = column[String]("email")

    def firstName: Rep[String] = column[String]("firstName")

    def lastName: Rep[String] = column[String]("lastName")

    def * : ProvenShape[UserDetails] = (userId, gender, userName, password, email, firstName, lastName).<>(UserDetails.tupled, UserDetails.unapply)

  }

  val userDetailsRef = TableQuery[UserDetailsTable]

  class UserDetailsRepository(implicit ec: ExecutionContext) {
    this: DB =>
    def all: Future[Seq[UserDetails]] = db.run {
      userDetailsRef.result
    }

    def registerUser(user: UserDetails): Future[Int] = db.run {
      userDetailsRef += user
    }

    def authentiacteUser(uid: Int): Future[Seq[UserDetails]] = db.run {
            userDetailsRef.filter(x => x.userId === uid).result
       }

  }

}
