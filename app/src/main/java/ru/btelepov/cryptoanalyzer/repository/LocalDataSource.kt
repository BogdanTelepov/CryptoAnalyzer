package ru.btelepov.cryptoanalyzer.repository

import androidx.lifecycle.LiveData
import ru.btelepov.cryptoanalyzer.database.MainDao

import ru.btelepov.cryptoanalyzer.models.CryptoCoin
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mainDao: MainDao) {

    fun readAllCoins(): LiveData<List<CryptoCoin>> {
        return mainDao.readAllCoins()
    }

    suspend fun insertCoinEntity(cryptoCoin: List<CryptoCoin>) {
        mainDao.insertCoinEntity(cryptoCoin)
    }

    fun searchCoin(query: String): LiveData<List<CryptoCoin>> {
        return mainDao.searchCoin(query)
    }
}