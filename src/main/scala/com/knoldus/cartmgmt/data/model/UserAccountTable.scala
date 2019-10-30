package com.knoldus.cartmgmt.data.model

import com.knoldus.cartmgmt.config.DB
import slick.lifted.ProvenShape

trait UserAccountTable {
  this: DB =>
  import driver.api._

  class UserAccountTable(tag: Tag) extends Table[UserAccount](tag, "USER_ACCOUNT") {
    def userId: Rep[Int] = column[Int]("userId", O.PrimaryKey)

    def userName: Rep[String] = column[String]("userName")

    def balance: Rep[Double] = column[Double]("balance")

    def * : ProvenShape[UserAccount] = (userId, userName, balance).<>(UserAccount.tupled, UserAccount.unapply)
  }

  val userAccountRef = TableQuery[UserAccountTable]
}
