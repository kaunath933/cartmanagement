package com.knoldus.cartmgmt.data.persistence

import com.knoldus.cartmgmt.data.model._
import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

import scala.concurrent.{ExecutionContext, Future}

trait AccountUser {

  class UserAccountTable(tag: Tag) extends Table[UserAccount](tag, "USER_ACCOUNT") {
    def userId: Rep[Int] = column[Int]("userId", O.PrimaryKey)

    def userName: Rep[String] = column[String]("userName")

    def balance: Rep[Double] = column[Double]("balance")

    def * : ProvenShape[UserAccount] = (userId, userName, balance).<>(UserAccount.tupled, UserAccount.unapply)
  }

  val userAccountRef = TableQuery[UserAccountTable]

  class UserAccountRepo(implicit ec2: ExecutionContext) {
    this: DB =>
    def all: Future[Seq[UserAccount]] = db.run {
      userAccountRef.result
    }

  }

}
