package com.example.russiansport.data.impls

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.russiansport.data.database.db.AppDatabase
import com.example.russiansport.data.database.mappers.MapperNews
import com.example.russiansport.data.network.retrofit.ApiFactory
import com.example.russiansport.domain.pojo.NewsUnit
import com.example.russiansport.domain.repositories.RepositoryNewsUnit

class RepositoryNewsUnitImpl(application: Application):RepositoryNewsUnit {

    private val db = AppDatabase.getInstance(application).dao()
    private val retrofit = ApiFactory.apiService
    private val mapper = MapperNews()

    override fun getNewsUnitList(): LiveData<List<NewsUnit>> {
        return Transformations.map(db.getNewsDbModelList()){
            it.map { mapper.mapDbModelToEntity(it) }
        }
    }

    override suspend fun loadNewsList() {
        try {
            val dto = retrofit.loadNewsList()
            val dbModel = dto.map { mapper.mapDtoToDbModel(it) }
            db.insertNewsListDbModel(dbModel)
        }catch (e:Exception){}
    }
}