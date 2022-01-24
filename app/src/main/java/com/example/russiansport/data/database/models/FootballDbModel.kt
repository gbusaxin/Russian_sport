package com.example.russiansport.data.database.models

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "table_football")
data class FootballDbModel (
    @PrimaryKey
    val league: String,

    val image: String,

    val country: String,

    val category: String,

    val dates: String
)