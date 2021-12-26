package com.example.russiansport.data.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "http://95.217.132.144/russian_sport/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}