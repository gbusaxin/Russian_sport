package com.example.russiansport.domain.repositories

import androidx.lifecycle.LiveData
import com.example.russiansport.domain.pojo.MatchUnit

interface RepositoryMatchUnit {
    fun getMatchesList():LiveData<List<MatchUnit>>
    fun getMatchesBySportList(sport:String):LiveData<List<MatchUnit>>
    suspend fun deleteMatchesList()
    suspend fun loadMatchesList()
}