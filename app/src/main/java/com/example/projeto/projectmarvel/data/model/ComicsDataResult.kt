package com.example.projeto.projectmarvel.data.model

import com.google.gson.annotations.SerializedName

data class ComicsDataResult(
    @SerializedName("offset") val offset : Int,
    @SerializedName("limit") val limit : Int,
    @SerializedName("total") val total : Int,
    @SerializedName("count") val count : Int,
    @SerializedName("results") val results : List<ComicsData>
)
