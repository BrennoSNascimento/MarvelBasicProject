package com.example.projeto.projectmarvel.data.model

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("path") val path : String,
    @SerializedName("extension") val extension : String
)
