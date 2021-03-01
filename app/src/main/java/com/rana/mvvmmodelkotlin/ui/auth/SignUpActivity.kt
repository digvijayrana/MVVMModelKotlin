package com.rana.mvvmmodelkotlin.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rana.mvvmmodelkotlin.R
import com.rana.mvvmmodelkotlin.data.db.entities.User
import com.rana.mvvmmodelkotlin.databinding.ActivitySignUpBinding
import com.rana.mvvmmodelkotlin.ui.home.HomeActivity
import com.rana.mvvmmodelkotlin.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(),AuthListner,KodeinAware {
    override val kodein by kodein()
    private val  factory:AuthViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySignUpBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        val viewModel= ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel= viewModel
        viewModel.authListner=this
        viewModel.getLoggedInUser().observe(this, Observer {user->
            if (user!=null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }
    override fun onStarted() {
        progress_bar.visibility= View.VISIBLE

    }

    override fun onSuccesss(user: User) {
        progress_bar.visibility= View.GONE
        toast("${user.name} is Logged In")
        toast("${user.created_at} is Logged In")

    }

    override fun onFailer(message: String) {
        progress_bar.visibility= View.GONE
        toast(message)

    }
}