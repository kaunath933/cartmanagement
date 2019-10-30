package com.knoldus.cartmgmt.service

import com.knoldus.cartmgmt.data.Dao.{MySqlDB, UserAccountDao, UserAccountRepoDaoImpl}

import scala.concurrent.ExecutionContext.Implicits.global

trait UserAccountServiceImpl extends UserAccountService {

  val userAccountDao: UserAccountDao

  def getAll = {
    userAccountDao.all
  }

}

object UserAccountServiceImpl extends UserAccountServiceImpl {
  val userAccountDao = new UserAccountRepoDaoImpl with MySqlDB

}
