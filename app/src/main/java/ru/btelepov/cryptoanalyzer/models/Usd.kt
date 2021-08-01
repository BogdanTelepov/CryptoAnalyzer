package ru.btelepov.cryptoanalyzer.models


import com.google.gson.annotations.SerializedName

data class Usd(


    @SerializedName("price")
    val price: Double?,

)