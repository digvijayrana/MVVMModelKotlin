package com.rana.mvvmmodelkotlin.repositry

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rana.mvvmmodelkotlin.data.db.AppDatabase
import com.rana.mvvmmodelkotlin.data.db.entities.Quote
import com.rana.mvvmmodelkotlin.data.prefences.PregenceProvider
import com.rana.mvvmmodelkotlin.network.MyApi
import com.rana.mvvmmodelkotlin.network.SafeApiRequest
import com.rana.mvvmmodelkotlin.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
private val MINIMUM_INTERVAL=6
class QuotesReprository(
    private val api:MyApi,
    private val db:AppDatabase,
    private val prefs:PregenceProvider
):SafeApiRequest(){
    private val quotes = MutableLiveData<List<Quote>>()
    init {
        quotes.observeForever{
            saveQuotes(it)
        }
    }
    private suspend fun fetchQuotes(){
        val lastSavedAt = prefs.getLastSavedAt()
        if (lastSavedAt==null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))){
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
    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt,LocalDateTime.now())> MINIMUM_INTERVAL
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                prefs.savelastSavedAt(LocalDateTime.now().toString())
                db.getQuoteDao().saveAllQuetes(quotes)
//            }

        }
    }
}