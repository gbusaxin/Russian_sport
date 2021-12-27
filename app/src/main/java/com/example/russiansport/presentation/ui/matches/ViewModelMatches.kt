package com.example.russiansport.presentation.ui.matches

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.russiansport.data.impls.RepositoryMatchUnitImpl
import com.example.russiansport.domain.use_cases.DeleteMatchesListUseCase
import com.example.russiansport.domain.use_cases.GetMatchesBySportUseCase
import com.example.russiansport.domain.use_cases.GetMatchesUnitListUseCase
import com.example.russiansport.domain.use_cases.LoadMatchesUnitListUseCase
import kotlinx.coroutines.launch

class ViewModelMatches(application: Application):AndroidViewModel(application) {
    private val repository = RepositoryMatchUnitImpl(application)

    private val deleteMatchesUseCase = DeleteMatchesListUseCase(repository)
    private val getMatchesListUseCase = GetMatchesUnitListUseCase(repository)
    private val loadMatchesUseCase = LoadMatchesUnitListUseCase(repository)
    private val getMatchesBySportUseCase = GetMatchesBySportUseCase(repository)

    fun getMatchesBySport(sport:String) = getMatchesBySportUseCase(sport)

    val matchesList = getMatchesListUseCase()

    fun deleteAllData() = deleteMatchesUseCase()

    init {
        viewModelScope.launch {
            loadMatchesUseCase()
        }
    }
}