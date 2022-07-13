package com.ofek.sample.data.android

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class AndroidSystemManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AndroidSystemManager {
    override fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            ) ?: false
    }
}