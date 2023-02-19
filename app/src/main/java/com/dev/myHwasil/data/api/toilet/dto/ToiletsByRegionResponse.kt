package com.dev.myHwasil.data.api.toilet.dto

import com.google.gson.annotations.SerializedName

data class ToiletsByRegionResponse(

    @SerializedName("id")
    val code: Int,

    @SerializedName("data")
    val toilet: String,
)
