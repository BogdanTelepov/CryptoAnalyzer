package ru.btelepov.cryptoanalyzer.viewModels


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

import ru.btelepov.cryptoanalyzer.models.CryptoCoin
import ru.btelepov.cryptoanalyzer.models.CryptoResponse
import ru.btelepov.cryptoanalyzer.repository.Repository
import ru.btelepov.cryptoanalyzer.utils.NetworkHandler
import ru.btelepov.cryptoanalyzer.utils.NetworkResult
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel
@Inject constructor(private val repository: Repository, application: Application) :
    AndroidViewModel(application) {


    /** Room */

    val readAllCoins: LiveData<List<CryptoCoin>> = repository.locale.readAllCoins()


    private fun insertCoinEntity(cryptoCoinList: List<CryptoCoin>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.locale.insertCoinEntity(cryptoCoinList)
        }
    }

    fun searchFromDatabase(query: String): LiveData<List<CryptoCoin>> {
        return repository.locale.searchCoin(query)
    }


    /** Retrofit */

    private val networkHandler: NetworkHandler by lazy { NetworkHandler(application.applicationContext) }
    private var _cryptoResponse: MutableLiveData<NetworkResult<CryptoResponse>> = MutableLiveData()
    val cryptoResponse: LiveData<NetworkResult<CryptoResponse>> get() = _cryptoResponse

    fun fetchLastCryptoCurrency() {
        viewModelScope.launch {
            getLastCurrency()
        }
    }


    private suspend fun getLastCurrency() {
        _cryptoResponse.value = NetworkResult.Loading()

        if (networkHandler.isNetworkAvailable()) {
            try {
                val response = repository.remote.getLastCryptoCurrency()
                _cryptoResponse.value = handleResponse(response)

                val cryptoCoinItem = _cryptoResponse.value!!.data
                if (cryptoCoinItem != null) {
                    offlineCacheCoins(cryptoCoinItem)
                }

            } catch (e: Exception) {
                _cryptoResponse.value = NetworkResult.Error("Что то пошло не так...")
                Log.d("HOME_VIEW_MODEL", e.message.toString())
            }
        } else {
            _cryptoResponse.value = NetworkResult.Error("Нет интернет соединения")
        }

    }

    private fun offlineCacheCoins(cryptoResponse: CryptoResponse) {
        val coinEntity = cryptoResponse.data
        insertCoinEntity(coinEntity)
    }


    private fun <T> handleResponse(response: Response<T>): NetworkResult<T> {
        return when {
            response.isSuccessful -> {
                val responseBody = response.body()
                NetworkResult.Success(responseBody)
            }

            response.code() == 400 -> {
                NetworkResult.Error(response.message().toString())
            }
            response.code() == 401 -> {
                NetworkResult.Error(response.message().toString())
            }
            response.code() == 403 -> {
                NetworkResult.Error(response.message().toString())
            }
            response.code() == 404 -> {
                NetworkResult.Error(response.message().toString())
            }
            else -> {
                NetworkResult.Error(response.message().toString())
            }
        }
    }


}