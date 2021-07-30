package ru.btelepov.cryptoanalyzer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import ru.btelepov.cryptoanalyzer.models.CryptoCoin

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinEntity(cryptoCoinsList: List<CryptoCoin>)

    @Query("SELECT * FROM crypto_coins_table")
    fun readAllCoins(): LiveData<List<CryptoCoin>>

    @Query("SELECT * FROM crypto_coins_table WHERE name LIKE :searchQuery")
    fun searchCoin(searchQuery: String): LiveData<List<CryptoCoin>>

}