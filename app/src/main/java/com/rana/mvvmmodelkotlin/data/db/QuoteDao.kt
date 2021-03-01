package com.rana.mvvmmodelkotlin.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rana.mvvmmodelkotlin.data.db.entities.Quote

@Dao
interface QuoteDao{
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun saveAllQuetes(quotes:List<Quote>)

    @Query("SELECT *FROM QUOTE")
    fun getQuotes():LiveData<List<Quote>>
}