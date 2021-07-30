package ru.btelepov.cryptoanalyzer.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.btelepov.cryptoanalyzer.models.*

class CustomTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun cryptoResponseToString(cryptoResponse: CryptoResponse): String {
        return gson.toJson(cryptoResponse)
    }

    @TypeConverter
    fun toCryptoResponse(data: String): CryptoResponse {
        val listType = object : TypeToken<CryptoResponse>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun cryptoCoinToString(cryptoCoin: CryptoCoin): String {
        return gson.toJson(cryptoCoin)
    }

    @TypeConverter
    fun fromStringToCryptoCoin(data: String): CryptoCoin {
        val listType = object : TypeToken<CryptoCoin>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun quoteToString(quote: Quote): String {
        return gson.toJson(quote)
    }

    @TypeConverter
    fun fromStringToQuote(data: String): Quote {
        val listType = object : TypeToken<Quote>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun statusToJson(status: Status): String {
        return gson.toJson(status)
    }

    @TypeConverter
    fun fromStringToStatus(data: String): Status {
        val listType = object : TypeToken<Status>() {}.type
        return gson.fromJson(data, listType)
    }
    @TypeConverter
    fun usdToString(usd:USD):String{
        return gson.toJson(usd)
    }
    @TypeConverter
    fun fromStringToUsd(data:String):USD{
        val listType = object :TypeToken<USD>(){}.type
        return gson.fromJson(data,listType)
    }
}