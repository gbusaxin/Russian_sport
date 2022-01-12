package com.example.russiansport.domain.pojo

import androidx.annotation.Keep

@Keep
data class NewsUnit(
    val title: String,

    val date: String,

    val image: String,

    val sport: String,

    val shortDescription: String,

    val description: String
)