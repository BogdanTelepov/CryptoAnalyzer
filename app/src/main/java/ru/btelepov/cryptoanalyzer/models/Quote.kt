package ru.btelepov.cryptoanalyzer.models


import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("USD")
    val usd: Usd
)