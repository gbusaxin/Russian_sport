package com.example.russiansport.data.network.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class ResponseDto(
    @SerializedName("url")
    var response: String
)