package com.example.russiansport.domain.use_cases

import com.example.russiansport.domain.repositories.RepositoryTournament

class LoadHockeyListUseCase(private val repositoryTournament: RepositoryTournament) {
    suspend operator fun invoke() = repositoryTournament.loadHockey()
}