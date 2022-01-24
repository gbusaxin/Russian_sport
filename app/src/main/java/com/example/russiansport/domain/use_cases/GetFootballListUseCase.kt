package com.example.russiansport.domain.use_cases

import com.example.russiansport.domain.repositories.RepositoryTournament

class GetFootballListUseCase(private val repositoryTournament: RepositoryTournament) {
    operator fun invoke() = repositoryTournament.getFootball()
}