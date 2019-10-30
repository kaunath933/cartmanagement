package com.knoldus.cartmgmt.data.Dao

import com.knoldus.cartmgmt.data.model.UserAccount

import scala.concurrent.Future

trait UserAccountDao {
  def all: Future[Seq[UserAccount]]
}
