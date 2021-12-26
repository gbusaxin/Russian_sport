package com.example.russiansport.presentation.ui.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.russiansport.data.impls.RepositoryNewsUnitImpl
import com.example.russiansport.domain.use_cases.GetNewsUnitListUseCase
import com.example.russiansport.domain.use_cases.LoadNewsUnitListUseCase
import kotlinx.coroutines.launch

class ViewModelNews (application: Application): AndroidViewModel(application) {
    private val repository = RepositoryNewsUnitImpl(application)
    private val getNewsUnitListUseCase = GetNewsUnitListUseCase(repository)
    private val loadNewsUnitListUseCase = LoadNewsUnitListUseCase(repository)

    val newsList = getNewsUnitListUseCase()

    init {
        viewModelScope.launch {
            loadNewsUnitListUseCase()
        }
    }
}