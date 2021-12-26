package com.example.russiansport.data.network.dto

import androidx.annotation.Keep
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



@Keep
data class NewsDto (
    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("date")
    @Expose
    val date: String? = null,

    @SerializedName("image")
    @Expose
    val image: String? = null,

    @SerializedName("sport")
    @Expose
    val sport: String? = null,

    @SerializedName("shortDescription")
    @Expose
    val shortDescription: String? = null,

    @SerializedName("description")
    @Expose
    val description: String? = null
    )