package com.knoldus.cartmgmt.data.Dao

import com.knoldus.cartmgmt.config.DB
import com.knoldus.cartmgmt.data.model._

import scala.concurrent.{ExecutionContext, Future}

class UserAccountRepoDaoImpl(implicit ec2: ExecutionContext) extends UserAccountDao with UserAccountTable {
  this: DB =>

  import driver.api._

  def all: Future[Seq[UserAccount]] = db.run {
    userAccountRef.result
  }

}

