package com.knoldus.cartmgmt.data.Dao

import com.knoldus.cartmgmt.config.DB
import com.knoldus.cartmgmt.data.model.UserCart

import scala.concurrent.{ ExecutionContext, Future }

trait UserCartDao {

  def all(id: Int): Future[Vector[Float]]

  def checkUIid(itemId: Int, userId: Int): Future[Seq[UserCart]]

  def addQuantity(newItem: UserCart): Future[Int]

  def addNewRow(newItem: UserCart): Future[Int]
}
