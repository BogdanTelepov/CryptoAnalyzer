package ru.btelepov.cryptoanalyzer.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import ru.btelepov.cryptoanalyzer.models.CryptoCoinItem

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinEntity(cryptoCoinsListItem: List<CryptoCoinItem>)

    @Query("SELECT * FROM crypto_coins_table")
    fun readAllCoins(): LiveData<List<CryptoCoinItem>>

    @Query("SELECT * FROM crypto_coins_table WHERE name LIKE :searchQuery")
    fun searchCoin(searchQuery: String): LiveData<List<CryptoCoinItem>>

}