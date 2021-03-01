package com.rana.mvvmmodelkotlin.network.responses

import com.rana.mvvmmodelkotlin.data.db.entities.User

data class AuthResponse (
         val isSuccess:Boolean?,
         val message:String?,
         val user:User?
 )
