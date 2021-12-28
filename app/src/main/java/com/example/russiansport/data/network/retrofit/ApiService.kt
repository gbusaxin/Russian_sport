package com.example.russiansport.data.network.retrofit

import com.example.russiansport.data.network.dto.MatchDto
import com.example.russiansport.data.network.dto.NewsDto
import com.example.russiansport.data.network.dto.TournamentDto
import retrofit2.http.GET

interface ApiService {

    @GET("sport_news.json")
    suspend fun loadNewsList():List<NewsDto>

    @GET("sport_matches.json")
    suspend fun loadMatchesList():List<MatchDto>

    @GET("sport_tournaments.json")
    suspend fun loadTournamentData():List<TournamentDto>
}