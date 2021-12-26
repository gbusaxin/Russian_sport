package com.example.russiansport.data.network.retrofit

import com.example.russiansport.data.network.dto.NewsDto
import retrofit2.http.GET

interface ApiService {

    @GET("sport_news.json")
    suspend fun loadNewsList():List<NewsDto>
}