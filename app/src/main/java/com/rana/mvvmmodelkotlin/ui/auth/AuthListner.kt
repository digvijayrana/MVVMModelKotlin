package com.rana.mvvmmodelkotlin.ui.auth

import com.rana.mvvmmodelkotlin.data.db.entities.User

//listen the button and edit text operation
interface AuthListner {
    fun onStarted()
    fun onSuccesss(user: User)
    fun onFailer(message:String)
}