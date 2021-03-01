package com.rana.mvvmmodelkotlin.repositry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rana.mvvmmodelkotlin.data.db.AppDatabase
import com.rana.mvvmmodelkotlin.data.db.entities.Quote
import com.rana.mvvmmodelkotlin.network.MyApi
import com.rana.mvvmmodelkotlin.network.SafeApiRequest
import com.rana.mvvmmodelkotlin.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesReprository(
    private val api:MyApi,
    private val db:AppDatabase
):SafeApiRequest(){
    private val quotes = MutableLiveData<List<Quote>>()
    init {
        quotes.observeForever{
            saveQuotes(it)
        }
    }
    private suspend fun fetchQuotes(){
        if (isFetchNeeded()){
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    suspend fun getQuotes():LiveData<List<Quote>>{
        return  withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }
    private fun isFetchNeeded(): Boolean {
        return true

    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            db.getQuoteDao().saveAllQuetes(quotes)
        }
    }
}