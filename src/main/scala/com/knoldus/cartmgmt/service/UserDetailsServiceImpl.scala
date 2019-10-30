package com.knoldus.cartmgmt.service

import com.knoldus.cartmgmt.data.Dao.{MySqlDB, UserDetailsDao, UserDetailsRepositoryDaoImpl}
import com.knoldus.cartmgmt.data.model.UserDetails

import scala.concurrent.ExecutionContext.Implicits.global

trait UserDetailsServiceImpl extends UserDetailsService {

  val userDetailsDao: UserDetailsDao

  def getAll = {
    userDetailsDao.all
  }

  def registerNewUser(user: UserDetails) = {
    userDetailsDao.registerUser(user)
  }

  def verifyUser(uid: Int) = {
    userDetailsDao.authentiacteUser(uid)

  }
}

object UserDetailsServiceImpl extends UserDetailsServiceImpl {

  val userDetailsDao = new UserDetailsRepositoryDaoImpl with MySqlDB

}
