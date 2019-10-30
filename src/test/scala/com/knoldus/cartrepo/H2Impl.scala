package com.knoldus.cartrepo

import com.knoldus.cartmgmt.config.DB
import slick.jdbc.JdbcProfile

trait H2Impl extends DB {

  val driver: JdbcProfile = slick.jdbc.H2Profile

  import driver.api._

  val db: driver.backend.DatabaseDef = Database.forConfig("h2url")

}
