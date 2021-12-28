package com.example.russiansport.data.impls

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.russiansport.data.database.db.AppDatabase
import com.example.russiansport.data.database.mappers.MapperTournament
import com.example.russiansport.data.network.retrofit.ApiFactory
import com.example.russiansport.domain.pojo.FootballUnit
import com.example.russiansport.domain.pojo.HockeyUnit
import com.example.russiansport.domain.pojo.TournamentsUnit
import com.example.russiansport.domain.repositories.RepositoryTournament

class RepositoryTournamentImpl(private val application: Application):RepositoryTournament {

    private val retrofit = ApiFactory.apiService
    private val db = AppDatabase.getInstance(application).dao()
    private val mapper = MapperTournament()



    override fun getTournamentUnit(): LiveData<List<TournamentsUnit>> {
        return Transformations.map(db.getTournamentDbModelList()){
            it.map { mapper.mapDbModelToEntity(it) }
        }
    }

    override suspend fun loadTournamentUnit() {
        try {
            val dto = retrofit.loadTournamentData()
            val dbModel = dto.map { mapper.mapDtoToDbModel(it) }
            db.insertTournamentDbModel(dbModel)
        }catch (e:Exception){}
    }
}