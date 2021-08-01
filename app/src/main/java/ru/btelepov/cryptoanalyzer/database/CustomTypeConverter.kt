package ru.btelepov.cryptoanalyzer.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.btelepov.cryptoanalyzer.models.*

class CustomTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun cryptoResponseToString(cryptoCoinResponse: CryptoCoinResponse): String {
        return gson.toJson(cryptoCoinResponse)
    }

    @TypeConverter
    fun toCryptoResponse(data: String): CryptoCoinResponse {
        val listType = object : TypeToken<CryptoCoinResponse>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun cryptoCoinToString(cryptoCoinItem: CryptoCoinItem): String {
        return gson.toJson(cryptoCoinItem)
    }

    @TypeConverter
    fun fromStringToCryptoCoin(data: String): CryptoCoinItem {
        val listType = object : TypeToken<CryptoCoinItem>() {}.type
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
    fun usdToString(usd:Usd):String{
        return gson.toJson(usd)
    }
    @TypeConverter
    fun fromStringToUsd(data:String):Usd{
        val listType = object :TypeToken<Usd>(){}.type
        return gson.fromJson(data,listType)
    }
}