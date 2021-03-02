package com.rana.mvvmmodelkotlin.data.prefences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.util.prefs.Preferences

private const val  KEYSAVED_AT = "Key_saved_at"
class PregenceProvider(context: Context){
    private val appContext = context.applicationContext
    private val prefence:SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appContext)
    fun savelastSavedAt(savedAt:String){
        prefence.edit().putString(KEYSAVED_AT,savedAt).apply()
    }
    fun getLastSavedAt():String?{
        return prefence.getString(KEYSAVED_AT,null)
    }
}