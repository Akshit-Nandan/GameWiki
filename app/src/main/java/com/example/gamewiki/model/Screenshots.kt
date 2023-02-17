package com.example.gamewiki.model

import com.google.gson.annotations.SerializedName

data class Screenshots(
    @SerializedName("id"    ) var id    : Int?    = null,
    @SerializedName("image" ) var image : String? = null
)
