package ru.btelepov.cryptoanalyzer.ui.viewModels


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

import ru.btelepov.cryptoanalyzer.models.CryptoCoinItem
import ru.btelepov.cryptoanalyzer.models.CryptoCoinResponse
import ru.btelepov.cryptoanalyzer.data.repository.Repository
import ru.btelepov.cryptoanalyzer.data.network.NetworkHandler
import ru.btelepov.cryptoanalyzer.data.network.NetworkResult
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel
@Inject constructor(private val repository: Repository, application: Application) :
    AndroidViewModel(application) {


    /** Room */


    val readAllCoins: LiveData<List<CryptoCoinItem>> get() = repository.locale.readAllCoins()


    private fun insertCoinEntity(cryptoCoinItemList: List<CryptoCoinItem>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.locale.insertCoinEntity(cryptoCoinItemList)
        }
    }

    fun searchFromDatabase(query: String): LiveData<List<CryptoCoinItem>> {
        return repository.locale.searchCoin(query)
    }


    /** Retrofit */

    private val networkHandler: NetworkHandler by lazy { NetworkHandler(application.applicationContext) }
    private var _cryptoCoinResponse: MutableLiveData<NetworkResult<CryptoCoinResponse>> =
        MutableLiveData()
    val cryptoCoinResponse: LiveData<NetworkResult<CryptoCoinResponse>> get() = _cryptoCoinResponse

    fun fetchLastCryptoCurrency() {
        viewModelScope.launch(Dispatchers.IO) {

            getLastCurrency()
        }
    }


    private suspend fun getLastCurrency() {
        _cryptoCoinResponse.postValue(NetworkResult.Loading())

        if (networkHandler.isNetworkAvailable()) {
            try {
                val response = repository.remote.getLastCryptoCurrency()
                _cryptoCoinResponse.postValue(handleResponse(response))

                val cryptoCoinItem = _cryptoCoinResponse.value!!.data
                if (cryptoCoinItem != null) {
                    offlineCacheCoins(cryptoCoinItem)
                }

            } catch (e: Exception) {
                _cryptoCoinResponse.postValue(NetworkResult.Error("Что то пошло не так..."))
                Log.d("HOME_VIEW_MODEL", e.message.toString())
            }
        } else {
            _cryptoCoinResponse.postValue(NetworkResult.Error("Нет интернет соединения"))
        }

    }

    private fun offlineCacheCoins(cryptoCoinResponse: CryptoCoinResponse) {
        val coinEntity = cryptoCoinResponse.data
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