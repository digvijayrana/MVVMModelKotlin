package com.rana.mvvmmodelkotlin.ui.home.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.rana.mvvmmodelkotlin.R
import com.rana.mvvmmodelkotlin.databinding.ProfileFragmentBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class Profile : Fragment(), KodeinAware {
    override val kodein by kodein()
    private lateinit var viewModel: ProfileViewModel
    private val factory: ProfileViewModelFactory by instance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding :ProfileFragmentBinding= DataBindingUtil.inflate(inflater,R.layout.profile__fragment,container,false)
        viewModel = ViewModelProvider(this,factory).get(ProfileViewModel::class.java)
        binding.viewmodel =viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}