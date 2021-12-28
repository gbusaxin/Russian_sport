package com.example.russiansport.data.database.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.russiansport.data.database.models.MatchDbModel
import com.example.russiansport.data.database.models.NewsDbModel
import com.example.russiansport.data.database.models.TournamentDbModel
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

    @Query("SELECT * FROM table_tournament")
    fun getTournamentDbModelList(): LiveData<List<TournamentDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTournamentDbModel(dbModel: List<TournamentDbModel>)
}