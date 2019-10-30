package com.knoldus.cartmgmt.data.Dao

import com.knoldus.cartmgmt.data.model.UserDetails

import scala.concurrent.Future

trait UserDetailsDao {
  def all: Future[Seq[UserDetails]]

  def registerUser(user: UserDetails): Future[Int]

  def authentiacteUser(uid: Int): Future[Seq[UserDetails]]

}
