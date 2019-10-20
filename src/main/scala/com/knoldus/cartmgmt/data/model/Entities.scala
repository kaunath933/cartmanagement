package com.knoldus.cartmgmt.data.model

case class UserDetails (userId: Int,
                        gender:String,
                 userName: String,
                 password: String,
                 email: String,
                 firstName:String,
                 lastName: String,
                 )

case class UserAccount (
                       userId:Int,
                       userName:String,
                       balance:Double,
                       )

case class UserCart(userId:Int,
                   itemId:Int,
                    quantity:Int,
                    price:Double
                   )
