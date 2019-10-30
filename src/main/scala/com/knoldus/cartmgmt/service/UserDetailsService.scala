package com.knoldus.cartmgmt.service

import com.knoldus.cartmgmt.data.model.UserDetails

import scala.concurrent.Future

trait UserDetailsService {

  def getAll: Future[Seq[UserDetails]]

  def registerNewUser(user: UserDetails): Future[Int]

  def verifyUser(uid: Int): Future[Seq[UserDetails]]

}
