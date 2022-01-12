package com.example.russiansport.data.database.models

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
@Keep
@Entity(tableName = "table_match")
data class MatchDbModel(
    @PrimaryKey(autoGenerate = true)
    val id :Int = 0,
    val team1: String,
    val image1: String,
    val team2: String,
    val image2: String,
    val date: String,
    val result: String,
    val tournament: String,
    val sport: String
)