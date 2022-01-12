package com.example.russiansport.data.network.dto

import androidx.annotation.Keep
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



@Keep
data class MatchDto (
    @SerializedName("team1")
    @Expose
    val team1: String? = null,

    @SerializedName("image1")
    @Expose
    val image1: String? = null,

    @SerializedName("team2")
    @Expose
    val team2: String? = null,

    @SerializedName("image2")
    @Expose
    val image2: String? = null,

    @SerializedName("date")
    @Expose
    val date: String? = null,

    @SerializedName("result")
    @Expose
    val result: String? = null,

    @SerializedName("tournament")
    @Expose
    val tournament: String? = null,

    @SerializedName("sport")
    @Expose
    val sport: String? = null
)