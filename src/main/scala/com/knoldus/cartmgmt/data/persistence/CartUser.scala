package com.knoldus.cartmgmt.data.persistence

import com.knoldus.cartmgmt.data.model._
import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

import scala.concurrent.{ExecutionContext, Future}

trait CartUser {

  class CartTableUser(tag: Tag) extends Table[UserCart](tag, "USER_CART") with DetailsUser {

    def userId: Rep[Int] = column[Int]("USER_ID")

    def itemId: Rep[Int] = column[Int]("ITEM_ID")

    def quantity: Rep[Int] = column[Int]("QUANTITY")

    def price: Rep[Double] = column[Double]("PRICE")

    def * : ProvenShape[UserCart] = (userId, itemId, quantity, price).<>(UserCart.tupled, UserCart.unapply)

  }

  val userCartRef = TableQuery[CartTableUser]

  class UserCartRepo(implicit ec3: ExecutionContext) {
    this: DB =>

    def all(id:Int): Future[Vector[Float]] =db.run {
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
}
