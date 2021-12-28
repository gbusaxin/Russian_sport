package com.example.russiansport.domain.use_cases

import com.example.russiansport.domain.repositories.RepositoryTournament

class LoadTournamentUseCase(private val repository:RepositoryTournament) {
    suspend operator fun invoke() = repository.loadTournamentUnit()
}