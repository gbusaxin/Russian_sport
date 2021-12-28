package com.example.russiansport.presentation.ui.tournaments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.russiansport.data.impls.RepositoryTournamentImpl
import com.example.russiansport.domain.use_cases.GetTournamentUnitUseCase
import com.example.russiansport.domain.use_cases.LoadTournamentUseCase
import kotlinx.coroutines.launch

class ViewModelTournament(application: Application):AndroidViewModel(application) {
    private val repository = RepositoryTournamentImpl(application)
    private val getTournamentUseCase = GetTournamentUnitUseCase(repository)
    private val loadTournament = LoadTournamentUseCase(repository)

    val tournament = getTournamentUseCase()

    init {
        viewModelScope.launch {
            loadTournament()
        }
    }
}