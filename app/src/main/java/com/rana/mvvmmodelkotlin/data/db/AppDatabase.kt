package com.rana.mvvmmodelkotlin.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rana.mvvmmodelkotlin.data.db.entities.Quote
import com.rana.mvvmmodelkotlin.data.db.entities.User

@Database(
        entities = [User::class,Quote::class],
        version = 1
)
abstract class AppDatabase:RoomDatabase(){
    abstract fun getUserDao():UserDao
    abstract fun getQuoteDao():QuoteDao

    companion object{
        @Volatile
        private var instace:AppDatabase?=null
        private val Lock= Any()

       operator  fun invoke(context: Context)= instace?: synchronized(Lock){
           instace?: buildDatabase(context).also {
               instace= it
           }
       }
        private  fun buildDatabase(context: Context)=Room.databaseBuilder(context.applicationContext,
        AppDatabase::class.java,"MyDatabase.db").build()
    }
}