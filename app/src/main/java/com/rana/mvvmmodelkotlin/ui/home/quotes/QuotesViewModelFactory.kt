package com.rana.mvvmmodelkotlin.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rana.mvvmmodelkotlin.repositry.QuotesReprository

class QuotesViewModelFactory (
     private val  reprository:QuotesReprository
):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(reprository) as T
    }
}