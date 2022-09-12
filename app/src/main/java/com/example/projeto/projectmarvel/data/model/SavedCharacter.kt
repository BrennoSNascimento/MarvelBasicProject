package com.example.projeto.projectmarvel.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SavedCharacter(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("thumbnail") val thumbnail : Thumbnail
):Serializable
