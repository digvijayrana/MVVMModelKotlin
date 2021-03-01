package com.rana.mvvmmodelkotlin.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rana.mvvmmodelkotlin.repositry.UserRepository

class ProfileViewModelFactory (
     private val  reprository:UserRepository
):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(reprository) as T
    }
}