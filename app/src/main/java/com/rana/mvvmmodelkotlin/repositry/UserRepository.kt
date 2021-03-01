package com.rana.mvvmmodelkotlin.repositry
import com.rana.mvvmmodelkotlin.data.db.AppDatabase
import com.rana.mvvmmodelkotlin.data.db.entities.User
import com.rana.mvvmmodelkotlin.network.MyApi
import com.rana.mvvmmodelkotlin.network.SafeApiRequest
import com.rana.mvvmmodelkotlin.network.responses.AuthResponse


class UserRepository (
        private val  api:MyApi,
        private  val db:AppDatabase
) : SafeApiRequest(){
    suspend fun userLogin(email:String,password:String):AuthResponse{
        return apiRequest { api.userLogin(email, password) }
    }
    suspend fun userSignUp(email: String,password: String,name:String):AuthResponse{
        return apiRequest { api.userSignUp(email, password, name) }
    }
    suspend fun saveUser(user: User)= db.getUserDao().upsert(user)
    fun getUser()= db.getUserDao().getuser()
}