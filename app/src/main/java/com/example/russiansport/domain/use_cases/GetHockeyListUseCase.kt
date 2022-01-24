package com.example.russiansport.domain.use_cases

import com.example.russiansport.domain.repositories.RepositoryTournament

class GetHockeyListUseCase(private val repositoryTournament: RepositoryTournament) {
    operator fun invoke() = repositoryTournament.getHockey()
}