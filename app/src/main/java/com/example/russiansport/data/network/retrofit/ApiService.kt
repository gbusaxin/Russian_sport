package com.example.russiansport.data.network.retrofit

import com.example.russiansport.data.network.dto.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("sport_news.json")
    suspend fun loadNewsList():List<NewsDto>

    @GET("sport_matches.json")
    suspend fun loadMatchesList():List<MatchDto>

    @GET("sport_hockey.json")
    suspend fun loadHockeyList():List<HockeyDto>

    @GET("sport_football.json")
    suspend fun loadFootballList():List<FootballDto>

    @POST("splash.php")
    fun sendLocale(@Body locale: RequestDto): Call<ResponseDto>

}