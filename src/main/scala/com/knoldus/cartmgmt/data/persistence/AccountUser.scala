package com.knoldus.cartmgmt.data.persistence

import com.knoldus.cartmgmt.data.model._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext

trait AccountUser {

  class UserAccountTable(tag: Tag) extends Table[UserAccount](tag, "USER_ACCOUNT") {
    def userId = column[Int]("userId", O.PrimaryKey)

    def userName = column[String]("userName")

    def balance = column[Double]("balance")

    def * = (userId, userName, balance).<>(UserAccount.tupled, UserAccount.unapply)
  }

  val userAccountRef = TableQuery[UserAccountTable]

  class UserAccountRepo(implicit ec2: ExecutionContext) {
    this: DB =>
    def all = db.run {
      userAccountRef.result
    }

  }

}
