package com.knoldus.cartmgmt.data.model

import com.knoldus.cartmgmt.config.DB
import slick.lifted.ProvenShape

trait UserDetailsTable {

  this: DB =>

  import driver.api._

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
}
