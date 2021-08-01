package ru.btelepov.cryptoanalyzer.data.repository

import androidx.lifecycle.LiveData
import ru.btelepov.cryptoanalyzer.data.database.MainDao

import ru.btelepov.cryptoanalyzer.models.CryptoCoinItem
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mainDao: MainDao) {

    fun readAllCoins(): LiveData<List<CryptoCoinItem>> {
        return mainDao.readAllCoins()
    }

    suspend fun insertCoinEntity(cryptoCoinItem: List<CryptoCoinItem>) {
        mainDao.insertCoinEntity(cryptoCoinItem)
    }

    fun searchCoin(query: String): LiveData<List<CryptoCoinItem>> {
        return mainDao.searchCoin(query)
    }
}