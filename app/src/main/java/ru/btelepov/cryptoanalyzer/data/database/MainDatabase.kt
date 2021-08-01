package ru.btelepov.cryptoanalyzer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import ru.btelepov.cryptoanalyzer.models.CryptoCoinItem

@Database(
    entities = [CryptoCoinItem::class], version = 1, exportSchema = false
)

@TypeConverters(CustomTypeConverter::class)
abstract class MainDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao
}