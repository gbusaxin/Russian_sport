package com.example.russiansport.presentation.ui.tournaments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.russiansport.data.impls.RepositoryTournamentImpl
import com.example.russiansport.domain.use_cases.GetFootballListUseCase
import com.example.russiansport.domain.use_cases.GetHockeyListUseCase
import com.example.russiansport.domain.use_cases.LoadFootballListUseCase
import com.example.russiansport.domain.use_cases.LoadHockeyListUseCase
import kotlinx.coroutines.launch

class ViewModelTournament(application: Application):AndroidViewModel(application) {
    private val repository = RepositoryTournamentImpl(application)

    private val getHockeyListUseCase = GetHockeyListUseCase(repository)
    private val getFootballListUseCase = GetFootballListUseCase(repository)
    private val loadHockeyListUseCase = LoadHockeyListUseCase(repository)
    private val loadFootballListUseCase = LoadFootballListUseCase(repository)

    val getHockeyList = getHockeyListUseCase()
    val getFootballList = getFootballListUseCase()

    init {
        viewModelScope.launch {
            loadFootballListUseCase()
            loadHockeyListUseCase()
        }
    }
}