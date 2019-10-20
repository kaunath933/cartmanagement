package com.knoldus.cartmgmt.data.persistence

import slick.jdbc.JdbcProfile

trait MySqlDB extends DB {

  val driver: JdbcProfile = slick.jdbc.MySQLProfile

  import driver.api._

  val db: driver.backend.DatabaseDef = Database.forConfig("mysql")
}
