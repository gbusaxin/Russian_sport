package com.example.russiansport.data.network.dto

import com.example.russiansport.domain.pojo.FootballUnit
import com.example.russiansport.domain.pojo.HockeyUnit
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class TournamentDto(
    @SerializedName("footbal")
    @Expose
    val footbal: List<FootballUnit>? = null,

    @SerializedName("hockey")
    @Expose
    val hockey: List<HockeyUnit>? = null
)