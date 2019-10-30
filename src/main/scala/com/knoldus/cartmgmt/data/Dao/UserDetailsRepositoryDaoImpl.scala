package com.knoldus.cartmgmt.data.Dao

import com.knoldus.cartmgmt.config.DB
import com.knoldus.cartmgmt.data.model._

import scala.concurrent.{ExecutionContext, Future}

class UserDetailsRepositoryDaoImpl(implicit ec: ExecutionContext) extends UserDetailsDao with UserDetailsTable {
  this: DB =>

  import driver.api._

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
