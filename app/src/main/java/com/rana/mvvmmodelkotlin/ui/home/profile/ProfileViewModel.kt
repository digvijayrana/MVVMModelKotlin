package com.rana.mvvmmodelkotlin.ui.home.profile

import androidx.lifecycle.ViewModel
import com.rana.mvvmmodelkotlin.repositry.UserRepository

class ProfileViewModel(
    reprository:UserRepository
) : ViewModel() {
    val user = reprository.getUser()

}