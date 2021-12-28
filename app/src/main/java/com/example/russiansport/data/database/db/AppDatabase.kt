package com.example.russiansport.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.russiansport.data.database.models.MatchDbModel
import com.example.russiansport.data.database.models.NewsDbModel
import com.example.russiansport.data.database.models.TournamentDbModel

@Database(
    entities =
    [
        NewsDbModel::class,
        MatchDbModel::class,
        TournamentDbModel::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "sport.db"
        private var database: AppDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                database?.let { return it }
                val instance = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                database = instance
                return instance
            }
        }
    }

    abstract fun dao(): DbDao
}