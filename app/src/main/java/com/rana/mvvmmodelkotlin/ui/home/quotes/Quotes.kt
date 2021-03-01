package com.rana.mvvmmodelkotlin.ui.home.quotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.rana.mvvmmodelkotlin.R
import com.rana.mvvmmodelkotlin.util.Coroutines
import com.rana.mvvmmodelkotlin.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class Quotes : Fragment(),KodeinAware {
    override val kodein by kodein()
    private val factory:QuotesViewModelFactory by instance()
    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,factory).get(QuotesViewModel::class.java)
        Coroutines.main {
             val quotes =viewModel.quotes.await()
            quotes.observe(this, Observer{
                context?.toast(it.size.toString())
            })

        }
    }

}