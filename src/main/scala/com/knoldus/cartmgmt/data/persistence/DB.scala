package com.knoldus.cartmgmt.data.persistence

import slick.jdbc.JdbcProfile

trait DB {
  val driver: JdbcProfile

  import driver.api._

  val db: driver.backend.DatabaseDef

}

