package com.example.gamewiki.api

import com.example.gamewiki.uitility.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : MainApi by lazy {
        retrofit.create(MainApi::class.java)
    }
}