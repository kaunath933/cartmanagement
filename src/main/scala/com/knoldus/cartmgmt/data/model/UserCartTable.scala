package com.knoldus.cartmgmt.data.model

import com.knoldus.cartmgmt.config.DB
import slick.lifted.ProvenShape

trait UserCartTable {
  this: DB =>

  import driver.api._

  class UserCartTable(tag: Tag) extends Table[UserCart](tag, "USER_CART") {

    def userId: Rep[Int] = column[Int]("USER_ID")

    def itemId: Rep[Int] = column[Int]("ITEM_ID")

    def quantity: Rep[Int] = column[Int]("QUANTITY")

    def price: Rep[Double] = column[Double]("PRICE")

    def * : ProvenShape[UserCart] = (userId, itemId, quantity, price).<>(UserCart.tupled, UserCart.unapply)

  }

  val userCartRef = TableQuery[UserCartTable]

}
