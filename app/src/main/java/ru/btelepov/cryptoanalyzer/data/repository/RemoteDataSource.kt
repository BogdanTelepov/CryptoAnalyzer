package ru.btelepov.cryptoanalyzer.data.repository

import retrofit2.Response
import ru.btelepov.cryptoanalyzer.models.CryptoCoinResponse
import ru.btelepov.cryptoanalyzer.data.network.CryptoApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val cryptoApi: CryptoApi) {

    suspend fun getLastCryptoCurrency(): Response<CryptoCoinResponse> {
        return cryptoApi.getLastCryptoCurrency()

    }


}