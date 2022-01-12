package com.example.russiansport.domain.pojo

import androidx.annotation.Keep

@Keep
data class MatchUnit(

    val team1: String,


    val image1: String,


    val team2: String,


    val image2: String,


    val date: String,


    val result: String,


    val tournament: String,


    val sport: String
)