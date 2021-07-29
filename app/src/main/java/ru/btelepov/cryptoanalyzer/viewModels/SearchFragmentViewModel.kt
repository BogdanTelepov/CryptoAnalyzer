package ru.btelepov.cryptoanalyzer.viewModels

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import ru.btelepov.cryptoanalyzer.repository.Repository
import ru.btelepov.cryptoanalyzer.utils.NetworkResult
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : BaseViewModel() {


    override fun <T> handleResponse(response: Response<T>): NetworkResult<T> {
        TODO("Not yet implemented")
    }
}