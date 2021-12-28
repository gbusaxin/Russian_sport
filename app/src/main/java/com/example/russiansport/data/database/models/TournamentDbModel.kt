package com.example.russiansport.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.russiansport.data.database.TournamentConverter
import com.example.russiansport.domain.pojo.FootballUnit
import com.example.russiansport.domain.pojo.HockeyUnit
@Entity(tableName = "table_tournament")
@TypeConverters(TournamentConverter::class)
data class TournamentDbModel (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val footbal: List<FootballUnit>,
    val hockey: List<HockeyUnit>
)