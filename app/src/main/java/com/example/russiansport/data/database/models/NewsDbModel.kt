package com.example.russiansport.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_news")
data class NewsDbModel (
    @PrimaryKey
    val title: String,

    val date: String,

    val image: String,

    val sport: String,

    val shortDescription: String,

    val description: String
)