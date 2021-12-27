package com.example.russiansport.domain.use_cases

import com.example.russiansport.domain.repositories.RepositoryMatchUnit

class DeleteMatchesListUseCase(private val repository:RepositoryMatchUnit) {
    operator fun invoke() = repository.deleteMatchesList()
}