package com.example.russiansport.domain.use_cases

import com.example.russiansport.domain.repositories.RepositoryMatchUnit

class LoadMatchesUnitListUseCase(private val repository:RepositoryMatchUnit) {
    suspend operator fun invoke() = repository.loadMatchesList()
}