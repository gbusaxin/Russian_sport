package com.example.russiansport.domain.use_cases

import com.example.russiansport.domain.repositories.RepositoryMatchUnit

class GetMatchesBySportUseCase(private val repository: RepositoryMatchUnit) {
    operator fun invoke(sport:String) = repository.getMatchesBySportList(sport)
}