package com.example.russiansport.domain.use_cases

import com.example.russiansport.domain.repositories.RepositoryTournament

class GetTournamentUnitUseCase(private val repository:RepositoryTournament) {
    operator fun invoke() = repository.getTournamentUnit()
}