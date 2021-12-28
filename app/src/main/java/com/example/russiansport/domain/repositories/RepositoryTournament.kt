package com.example.russiansport.domain.repositories

import androidx.lifecycle.LiveData
import com.example.russiansport.domain.pojo.FootballUnit
import com.example.russiansport.domain.pojo.HockeyUnit
import com.example.russiansport.domain.pojo.TournamentsUnit

interface RepositoryTournament {

    fun getTournamentUnit():LiveData<List<TournamentsUnit>>
    suspend fun loadTournamentUnit()
}