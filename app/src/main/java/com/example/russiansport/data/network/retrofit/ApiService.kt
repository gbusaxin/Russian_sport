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

    @GET("sport_tournaments.json")
    suspend fun loadTournamentData():List<TournamentDto>

    @POST("splash.php")
    fun sendLocale(@Body locale: RequestDto): Call<ResponseDto>

}