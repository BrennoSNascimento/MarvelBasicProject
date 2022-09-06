package com.example.projeto.projectmarvel.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ItemList(
    @SerializedName("resourceURI") val resourceURI : String,
    @SerializedName("name") val name : String,
):Serializable
