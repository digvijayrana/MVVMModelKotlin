package com.rana.mvvmmodelkotlin.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.rana.mvvmmodelkotlin.repositry.QuotesReprository
import com.rana.mvvmmodelkotlin.util.lazyDeferred


class QuotesViewModel(
    repository:QuotesReprository
) : ViewModel() {
    val quotes by lazyDeferred{
        repository.getQuotes()
    }
}