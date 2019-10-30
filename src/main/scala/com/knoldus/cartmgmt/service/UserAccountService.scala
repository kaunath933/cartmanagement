package com.knoldus.cartmgmt.service

import com.knoldus.cartmgmt.data.model.UserAccount

trait UserAccountService {

  import scala.concurrent.{ ExecutionContext, Future }

  def getAll: Future[Seq[UserAccount]]

}
