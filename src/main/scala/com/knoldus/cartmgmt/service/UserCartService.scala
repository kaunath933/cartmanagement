package com.knoldus.cartmgmt.service

import com.knoldus.cartmgmt.data.model.UserCart

import scala.concurrent.{ ExecutionContext, Future }

trait UserCartService {

  def getAll(id: Int): Future[Vector[Float]]

  def verifyUIid(itemId: Int, userId: Int): Future[Seq[UserCart]]

  def insertQuantity(newItem: UserCart): Future[Int]

  def insertNewRow(newItem: UserCart): Future[Int]
}

