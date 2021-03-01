package com.rana.mvvmmodelkotlin.network

import com.rana.mvvmmodelkotlin.network.responses.AuthResponse
import com.rana.mvvmmodelkotlin.network.responses.QuotesResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
            @Field("email") email: String,
            @Field("password") password: String
    ) : Response<AuthResponse>

    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignUp(  @Field("email") email: String,
                             @Field("password") password: String,
                             @Field("name")name:String
    ):Response<AuthResponse>

    @GET("quotes")
    suspend fun getQuotes(): Response<QuotesResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterCepter: NetworkConnectionInterCepter
        ):MyApi{
            val okkHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterCepter).build()
            return  Retrofit.Builder().client(okkHttpClient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/").addConverterFactory(GsonConverterFactory.create()).build().create(MyApi::class.java)
        }
    }
}