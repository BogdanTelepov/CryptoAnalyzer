package ru.btelepov.cryptoanalyzer.models


import com.google.gson.annotations.SerializedName

data class CryptoCoinResponse(
    @SerializedName("data")
    val data: List<CryptoCoinItem>,
    @SerializedName("status")
    val status: Status
)