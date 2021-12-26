package com.example.russiansport.data.database.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.russiansport.data.database.models.NewsDbModel

@Dao
interface DbDao {

    @Query("SELECT * FROM table_news")
    fun getNewsDbModelList(): LiveData<List<NewsDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsListDbModel(dbModel: List<NewsDbModel>)
}