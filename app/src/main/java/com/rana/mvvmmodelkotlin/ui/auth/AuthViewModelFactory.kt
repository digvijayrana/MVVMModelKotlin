package com.rana.mvvmmodelkotlin.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rana.mvvmmodelkotlin.repositry.UserRepository

class AuthViewModelFactory (
     private val  reprository:UserRepository
):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(reprository) as T
    }
}