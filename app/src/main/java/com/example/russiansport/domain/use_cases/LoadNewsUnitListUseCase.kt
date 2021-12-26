package com.example.russiansport.domain.use_cases

import com.example.russiansport.domain.repositories.RepositoryNewsUnit

class LoadNewsUnitListUseCase(private val repository: RepositoryNewsUnit) {
    suspend operator fun invoke() = repository.loadNewsList()
}