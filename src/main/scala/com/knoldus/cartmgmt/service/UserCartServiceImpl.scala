package com.knoldus.cartmgmt.service

import com.knoldus.cartmgmt.data.Dao.{MySqlDB, UserCartDao, UserCartRepoDaoImpl}
import com.knoldus.cartmgmt.data.model.UserCart

import scala.concurrent.ExecutionContext.Implicits.global

trait UserCartServiceImpl extends UserCartService {
  val userCartDao: UserCartDao

  def getAll(id: Int) = {
    userCartDao.all(id)
  }

  def verifyUIid(itemId: Int, userId: Int) = {
    userCartDao.checkUIid(itemId, userId)
  }

  def insertQuantity(newItem: UserCart) = {
    userCartDao.addQuantity(newItem)

  }

  def insertNewRow(newItem: UserCart) = {
    userCartDao.addNewRow(newItem)
  }

}

object UserCartServiceImpl extends UserCartServiceImpl {
  val userCartDao: UserCartDao = new UserCartRepoDaoImpl with MySqlDB

}
