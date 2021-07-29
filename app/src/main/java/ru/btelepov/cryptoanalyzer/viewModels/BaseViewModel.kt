package ru.btelepov.cryptoanalyzer.viewModels


import androidx.lifecycle.ViewModel
import retrofit2.Response
import ru.btelepov.cryptoanalyzer.utils.NetworkResult


abstract class BaseViewModel : ViewModel() {


    abstract fun <T> handleResponse(response: Response<T>): NetworkResult<T>


}