package ru.btelepov.cryptoanalyzer.models


import com.google.gson.annotations.SerializedName

data class USD(


    @SerializedName("price")
    val price: Double,

)