package com.knoldus.cartmgmt.config

import slick.jdbc.JdbcProfile

trait DB {
  val driver: JdbcProfile
  import driver.api._
  val db: Database

  def close: Unit = {
    db.close()
  }
}
