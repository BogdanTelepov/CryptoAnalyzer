package ru.btelepov.cryptoanalyzer.models


import com.google.gson.annotations.SerializedName

data class CryptoResponse(
    @SerializedName("data")
    val data: List<CryptoCoin>,
    @SerializedName("status")
    val status: Status
)