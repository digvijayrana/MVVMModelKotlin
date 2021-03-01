package com.rana.mvvmmodelkotlin

import android.app.Application
import com.rana.mvvmmodelkotlin.data.db.AppDatabase
import com.rana.mvvmmodelkotlin.network.MyApi
import com.rana.mvvmmodelkotlin.network.NetworkConnectionInterCepter
import com.rana.mvvmmodelkotlin.repositry.QuotesReprository
import com.rana.mvvmmodelkotlin.repositry.UserRepository
import com.rana.mvvmmodelkotlin.ui.auth.AuthViewModelFactory
import com.rana.mvvmmodelkotlin.ui.home.profile.ProfileViewModelFactory
import com.rana.mvvmmodelkotlin.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication :Application(),KodeinAware{
    override val kodein= Kodein.lazy{

        import(androidXModule(this@MVVMApplication))
        bind() from singleton { NetworkConnectionInterCepter(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(),instance()) }
        bind() from provider { AuthViewModelFactory(instance())}
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from singleton { QuotesReprository(instance(),instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }

    }


}