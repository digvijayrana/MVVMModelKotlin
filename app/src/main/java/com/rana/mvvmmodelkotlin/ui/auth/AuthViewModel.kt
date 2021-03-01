package com.rana.mvvmmodelkotlin.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.rana.mvvmmodelkotlin.repositry.UserRepository
import com.rana.mvvmmodelkotlin.util.ApiException
import com.rana.mvvmmodelkotlin.util.Coroutines
import com.rana.mvvmmodelkotlin.util.NoInterNetException

//for SignUp and LogIn
class AuthViewModel(
    private val   repository: UserRepository
) : ViewModel() {
    var name:String? = null
    var email:String? = null
    var  password:String?= null
    var  passwordconfirm:String?= null
    var authListner:AuthListner?=null
    fun getLoggedInUser()= repository.getUser()
    fun onLoginButtonClick(view: View){
        authListner?.onStarted()
        if (email.isNullOrEmpty()|| password.isNullOrEmpty()){
            authListner?.onFailer("Invalid email or Password")
            return }
        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!,password!!)
                authResponse.user?.let {
                    authListner?.onSuccesss(it)
                    repository.saveUser(it)
                    return@main
                }
                authListner?.onFailer(authResponse.message!!)
            }catch (e:ApiException){
                authListner?.onFailer(e.message!!)
            }catch (e:NoInterNetException){
                authListner?.onFailer(e.message!!)
            }
        }
    }

    fun onSignUp(view: View){
        Intent(view.context,SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLogin(view: View){
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onSignUpButtonClick(view: View){
        authListner?.onStarted()

        if (name.isNullOrEmpty()){
            authListner?.onFailer("Name is Required")
            return
        }
        if (email.isNullOrEmpty()){
            authListner?.onFailer("Email is Required")
            return
        }
        if (password.isNullOrEmpty()){
            authListner?.onFailer("Please enter a Password")
            return
        }
        if (password != passwordconfirm){
            authListner?.onFailer("Password did't match")
            return
        }
        Coroutines.main {
            try {
                val authResponse = repository.userSignUp(name!!,email!!,password!!)
                authResponse.user?.let {
                    authListner?.onSuccesss(it)
                    repository.saveUser(it)
                    return@main
                }
                authListner?.onFailer(authResponse.message!!)
            }catch (e:ApiException){
                authListner?.onFailer(e.message!!)
            }catch (e:NoInterNetException){
                authListner?.onFailer(e.message!!)
            }
        }
    }
}