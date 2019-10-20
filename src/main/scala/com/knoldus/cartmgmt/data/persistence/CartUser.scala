package com.knoldus.cartmgmt.data.persistence

import com.knoldus.cartmgmt.data.model._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext

trait CartUser {

  class CartTableUser(tag: Tag) extends Table[UserCart](tag, "USER_CART") with DetailsUser {

    def userId = column[Int]("USER_ID")

    def itemId = column[Int]("ITEM_ID")

    def quantity = column[Int]("QUANTITY")

    def price = column[Double]("PRICE")

    def * = (userId, itemId, quantity, price).<>(UserCart.tupled, UserCart.unapply)

  }

  val userCartRef = TableQuery[CartTableUser]

  class UserCartRepo(implicit ec3: ExecutionContext) {
    this: DB =>

    def all(id:Int) =db.run {
      sql"""
      SELECT SUM(PRICE * QUANTITY)
      FROM USER_CART
        WHERE USER_ID = $id;
        """.as[(Float)]
    }

    def checkUIid(itemId: Int, userId: Int) = db.run {
      userCartRef.filter(x => (x.itemId === itemId && x.userId === userId)).result

    }

    def addQuantity(newItem: UserCart) = db.run {
      userCartRef += newItem
    }

    def addNewRow(newItem: UserCart) = db.run {
      userCartRef += newItem
    }
  }
}
