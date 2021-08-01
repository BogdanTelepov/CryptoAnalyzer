package ru.btelepov.cryptoanalyzer.network

import retrofit2.Response
import retrofit2.http.GET
import ru.btelepov.cryptoanalyzer.models.CryptoCoinResponse
import ru.btelepov.cryptoanalyzer.utils.Constants.GET_LAST_CRYPTO_CURRENCY

interface CryptoApi {

    @GET(GET_LAST_CRYPTO_CURRENCY)
    suspend fun getLastCryptoCurrency(): Response<CryptoCoinResponse>
}