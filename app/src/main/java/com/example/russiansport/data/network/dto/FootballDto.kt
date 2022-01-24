package com.example.russiansport.data.network.dto

import androidx.annotation.Keep
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



@Keep
data class FootballDto (
    @SerializedName("league")
    @Expose
    val league: String? = null,

    @SerializedName("image")
    @Expose
    val image: String? = null,

    @SerializedName("country")
    @Expose
    val country: String? = null,

    @SerializedName("category")
    @Expose
    val category: String? = null,

    @SerializedName("dates")
    @Expose
    val dates: String? = null
)