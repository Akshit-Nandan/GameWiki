package com.example.gamewiki.model

import com.google.gson.annotations.SerializedName

data class MinimumSystemRequirements(
    @SerializedName("os"        ) var os        : String? = null,
    @SerializedName("processor" ) var processor : String? = null,
    @SerializedName("memory"    ) var memory    : String? = null,
    @SerializedName("graphics"  ) var graphics  : String? = null,
    @SerializedName("storage"   ) var storage   : String? = null

)
