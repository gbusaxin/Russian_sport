package com.example.russiansport.domain.repositories

import androidx.lifecycle.LiveData
import com.example.russiansport.domain.pojo.NewsUnit

interface RepositoryNewsUnit {
    fun getNewsUnitList(): LiveData<List<NewsUnit>>
    suspend fun loadNewsList()
}