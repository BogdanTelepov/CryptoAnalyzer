package ru.btelepov.cryptoanalyzer.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.btelepov.cryptoanalyzer.models.CryptoResponse

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
}