package ru.btelepov.cryptoanalyzer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.btelepov.cryptoanalyzer.database.enteties.CryptoCoinEntity

@Database(
    entities = [CryptoCoinEntity::class], version = 1, exportSchema = false
)

@TypeConverters(CustomTypeConverter::class)
abstract class MainDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao
}