package com.example.russiansport.domain.repositories

import androidx.lifecycle.LiveData
import com.example.russiansport.domain.pojo.FootballUnit
import com.example.russiansport.domain.pojo.HockeyUnit
import com.example.russiansport.domain.pojo.TournamentsUnit

interface RepositoryTournament {

    fun getHockey():LiveData<List<HockeyUnit>>
    fun getFootball():LiveData<List<FootballUnit>>

    suspend fun loadHockey()
    suspend fun loadFootball()
}