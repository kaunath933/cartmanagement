package com.knoldus.cartmgmt.data.Dao

import com.knoldus.cartmgmt.config.DB
import com.knoldus.cartmgmt.data.model._

import scala.concurrent.{ExecutionContext, Future}

class UserCartRepoDaoImpl(implicit ec3: ExecutionContext) extends UserCartDao with UserCartTable {
  this: DB =>

  import driver.api._

  def all(id: Int): Future[Vector[Float]] = db.run {
    sql"""
      SELECT SUM(PRICE * QUANTITY)
      FROM USER_CART
        WHERE USER_ID = $id;
        """.as[(Float)]
  }

  def checkUIid(itemId: Int, userId: Int): Future[Seq[UserCart]] = db.run {
    userCartRef.filter(x => (x.itemId === itemId && x.userId === userId)).result

  }

  def addQuantity(newItem: UserCart): Future[Int] = db.run {
    userCartRef += newItem
  }

  def addNewRow(newItem: UserCart): Future[Int] = db.run {
    userCartRef += newItem
  }

}
