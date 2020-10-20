@file:Suppress("DEPRECATION")

package com.kryptkode.farmz.app.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.kryptkode.farmz.app.utils.livedata.extension.asLiveData
import timber.log.Timber


class NetworkListener(private val context: Context) : LifecycleObserver {
    private val mutableNetworkState = MutableLiveData<Boolean>()
    private val mutableNetworkInfo = MutableLiveData<NetworkStateWrapper>()

    val networkState = mutableNetworkState.asLiveData()
    val networkInfo = mutableNetworkInfo.asLiveData()

    private lateinit var receiver: BroadcastReceiver
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private val connectivityManager =
        context.applicationContext.getSystemService<ConnectivityManager>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun start() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Timber.d("Starting for API >= 21")
            setupConnectionListener()
        } else {
            Timber.d("Starting for API < 21")
            initializeReceiver()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stop() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            unregisterNetworkCallback()
        } else {
            unregisterReceiver()
        }
    }

    private fun unregisterReceiver() {
        Timber.d("Stopping for API < 21")
        context.unregisterReceiver(receiver)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun unregisterNetworkCallback() {
        Timber.d("Stopping for API >= 21")
        val connectivityManager =
            context.applicationContext.getSystemService<ConnectivityManager>()
        connectivityManager?.unregisterNetworkCallback(networkCallback)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupConnectionListener() {
        Timber.d("Setting up connectivity listener")
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                Timber.d(" lost connection")
                val networkInfo = connectivityManager?.getNetworkInfo(network)
                val networkStateWrapper = NetworkStateWrapper(false, networkInfo)
                mutableNetworkInfo.postValue(networkStateWrapper)
                mutableNetworkState.postValue(false)
            }

            override fun onAvailable(network: Network) {
                Timber.d(" gained connection")
                val networkInfo = connectivityManager?.getNetworkInfo(network)
                val networkStateWrapper = NetworkStateWrapper(true, networkInfo)
                mutableNetworkInfo.postValue(networkStateWrapper)
                mutableNetworkState.postValue(true)
            }
        }
        val networkRequest = NetworkRequest.Builder().build()
        connectivityManager?.registerNetworkCallback(networkRequest, networkCallback)
    }


    @Suppress("DEPRECATION")
    private fun initializeReceiver() {
        Timber.d("Initializing connectivity receiver")
        receiver = ConnectivityReceiver()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(receiver, filter)
    }


    @Suppress("DEPRECATION")
    inner class ConnectivityReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Timber.d("OnReceive")
            if (context != null) {
                val stateChange = isOffline()
                Timber.d("Network state changed to: %s", stateChange)
                val networkStateWrapper =
                    NetworkStateWrapper(stateChange, connectivityManager?.activeNetworkInfo)
                mutableNetworkInfo.postValue(networkStateWrapper)
                mutableNetworkState.postValue(stateChange)
            }
        }
    }

    @Suppress("DEPRECATION")
    fun isOffline(): Boolean {
        return !(connectivityManager?.activeNetworkInfo != null
                && connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true)
    }

    fun observeLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }
}

@Suppress("DEPRECATION")
data class NetworkStateWrapper(val connected: Boolean, val networkInfo: NetworkInfo?)
