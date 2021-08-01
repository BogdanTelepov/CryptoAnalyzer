package ru.btelepov.cryptoanalyzer.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "crypto_coins_table")
data class CryptoCoinItem(

    @SerializedName("cmc_rank")
    val cmcRank: Int,
    @SerializedName("date_added")
    val dateAdded: String,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("last_updated")
    val lastUpdated: String,

    @SerializedName("name")
    val name: String,


    @SerializedName("quote")
    val quote: Quote,

    @SerializedName("symbol")
    val symbol: String,


    )