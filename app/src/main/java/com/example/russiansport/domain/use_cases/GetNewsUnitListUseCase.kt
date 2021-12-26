package com.example.russiansport.domain.use_cases

import com.example.russiansport.domain.repositories.RepositoryNewsUnit

class GetNewsUnitListUseCase(private val repository: RepositoryNewsUnit) {
    operator fun invoke() = repository.getNewsUnitList()
}