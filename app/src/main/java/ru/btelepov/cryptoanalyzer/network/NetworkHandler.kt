package ru.btelepov.cryptoanalyzer.network

import android.content.Context
import android.net.NetworkCapabilities

import dagger.hilt.android.qualifiers.ApplicationContext
import ru.btelepov.cryptoanalyzer.extensions.connectivityManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler @Inject constructor(@ApplicationContext private val context: Context) {



    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.connectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }

}