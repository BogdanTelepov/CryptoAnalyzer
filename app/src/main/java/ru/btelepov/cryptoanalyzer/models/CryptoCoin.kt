package ru.btelepov.cryptoanalyzer.models


import com.google.gson.annotations.SerializedName

data class CryptoCoin(

    @SerializedName("cmc_rank")
    val cmcRank: Int,
    @SerializedName("date_added")
    val dateAdded: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_updated")
    val lastUpdated: String,

    @SerializedName("name")
    val name: String,
    @SerializedName("num_market_pairs")
    val numMarketPairs: Int,

    @SerializedName("quote")
    val quote: Quote,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("tags")
    val tags: List<String>,

)