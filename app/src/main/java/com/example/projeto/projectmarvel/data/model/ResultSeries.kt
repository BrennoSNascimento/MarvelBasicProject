package com.example.projeto.projectmarvel.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultSeries(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("startYear") val startYear : Int,
    @SerializedName("endYear") val endYear : Int,
    @SerializedName("rating") val rating : String,
    @SerializedName("type") val type : String,
    @SerializedName("thumbnail") val thumbnail : Thumbnail,
):Serializable
