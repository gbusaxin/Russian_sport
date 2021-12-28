package com.example.russiansport.data.impls

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.russiansport.data.database.db.AppDatabase
import com.example.russiansport.data.database.mappers.MapperMatch
import com.example.russiansport.data.network.retrofit.ApiFactory
import com.example.russiansport.domain.pojo.MatchUnit
import com.example.russiansport.domain.repositories.RepositoryMatchUnit

class RepositoryMatchUnitImpl(application: Application) : RepositoryMatchUnit {

    private val db = AppDatabase.getInstance(application).dao()
    private val retrofit = ApiFactory.apiService
    private val mapper = MapperMatch()

    override fun getMatchesList(): LiveData<List<MatchUnit>> {
        return Transformations.map(db.getMatchesDbModelList()) {
            it.map { mapper.mapDbModelToEntity(it) }
        }
    }

    override fun getMatchesBySportList(sport: String): LiveData<List<MatchUnit>> {
        return Transformations.map(db.getMatchBySport(sport)){
            it.map { mapper.mapDbModelToEntity(it) }
        }
    }

    override suspend fun deleteMatchesList() {
        db.deleteAll()
    }

    override suspend fun loadMatchesList() {
        try {
            val dto = retrofit.loadMatchesList()
            val dbModel = dto.map { mapper.mapDtoToDbModel(it) }
            db.insertMatchesListDbModel(dbModel)
        } catch (e: Exception) {
        }
    }
}