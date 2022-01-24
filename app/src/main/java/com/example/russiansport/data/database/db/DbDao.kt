package com.example.russiansport.data.database.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.russiansport.data.database.models.*
import com.example.russiansport.domain.pojo.MatchUnit

@Dao
interface DbDao {

    @Query("SELECT * FROM table_news")
    fun getNewsDbModelList(): LiveData<List<NewsDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsListDbModel(dbModel: List<NewsDbModel>)

    @Query("SELECT * FROM table_match")
    fun getMatchesDbModelList(): LiveData<List<MatchDbModel>>

    @Query("SELECT * FROM table_match WHERE sport ==:sport")
    fun getMatchBySport(sport:String):LiveData<List<MatchDbModel>>

    @Query("DELETE FROM table_match")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchesListDbModel(dbModel: List<MatchDbModel>)

    @Query("SELECT * FROM table_hockey")
    fun getHockeyList(): LiveData<List<HockeyDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHockeyList(dbModel: List<HockeyDbModel>)

    @Query("SELECT * FROM table_football")
    fun getFootballList(): LiveData<List<FootballDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFootballList(dbModel: List<FootballDbModel>)
}