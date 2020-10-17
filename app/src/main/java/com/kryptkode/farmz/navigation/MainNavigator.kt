package com.kryptkode.farmz.navigation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.NavController
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.NavDestination
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.utils.extension.beVisibleIf
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainNavigator @Inject constructor(
    private val navControllerProvider: NavControllerProvider
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun setup() {
        val loggedIn = false //TODO: Read locally
        val navView = navControllerProvider.getNavView()
        val navController = navControllerProvider.getNavController()
        val weakNavView = WeakReference(navView)
        navController.addOnDestinationChangedListener(DestinationChangedListener(weakNavView))

        val graph = navController.navInflater.inflate(R.navigation.main_navigation)
        graph.startDestination = when {
            loggedIn -> {
                R.id.main_navigation
            }
            else -> {
                R.id.login_navigation
            }
        }
        navController.graph = graph
    }

    class DestinationChangedListener(private val weakNavView: WeakReference<View?>) :
        OnDestinationChangedListener {

        override fun onDestinationChanged(
            controller: NavController,
            destination: NavDestination,
            arguments: Bundle?
        ) {
            if (weakNavView.get() == null) {
                controller.removeOnDestinationChangedListener(
                    this
                )
            }
            weakNavView.get()?.beVisibleIf(destination.id != R.id.loginFragment)
        }
    }
}