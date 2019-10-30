package com.knoldus.cartmgmt.data.model

case class UserCart(
  userId: Int,
  itemId: Int,
  quantity: Int,
  price: Double)
