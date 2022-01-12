package com.example.russiansport.domain.pojo

import androidx.annotation.Keep

@Keep
data class HockeyUnit(
    val league: String,

    val image: String,

    val country: String,

    val category: String,

    val dates: String
)