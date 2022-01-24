package com.example.russiansport.data.impls

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.russiansport.data.database.db.AppDatabase
import com.example.russiansport.data.database.mappers.MapperTournament
import com.example.russiansport.data.network.retrofit.ApiFactory
import com.example.russiansport.domain.pojo.FootballUnit
import com.example.russiansport.domain.pojo.HockeyUnit
import com.example.russiansport.domain.repositories.RepositoryTournament
import java.lang.Exception

class RepositoryTournamentImpl(private val application: Application):RepositoryTournament {

    private val retrofit = ApiFactory.apiService
    private val db = AppDatabase.getInstance(application).dao()
    private val mapper = MapperTournament()

    override fun getHockey(): LiveData<List<HockeyUnit>> {
        return Transformations.map(db.getHockeyList()){
            it.map { mapper.mapHockeyDbModelToEntity(it) }
        }
    }

    override fun getFootball(): LiveData<List<FootballUnit>> {
        return Transformations.map(db.getFootballList()){
            it.map { mapper.mapFootballDbModelToEntity(it) }
        }
    }

    override suspend fun loadHockey() {
        try {
            val dto = retrofit.loadHockeyList()
            val dbModel = dto.map { mapper.mapHockeyDtoToDbModel(it) }
            db.insertHockeyList(dbModel)
        }catch (e:Exception){

        }
    }

    override suspend fun loadFootball() {
        try {
            val dto = retrofit.loadFootballList()
            val dbModel = dto.map { mapper.mapFootballDtoToDbModel(it) }
            db.insertFootballList(dbModel)
        }catch (e:Exception){

        }
    }


}