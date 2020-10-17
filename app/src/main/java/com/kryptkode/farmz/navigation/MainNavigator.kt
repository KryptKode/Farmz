package com.kryptkode.farmz.navigation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.NavDestination
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.domain.AuthRepository
import com.kryptkode.farmz.app.utils.extension.beVisibleIf
import kotlinx.coroutines.flow.collect
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainNavigator @Inject constructor(
    private val activity: AppCompatActivity,
    private val navControllerProvider: NavControllerProvider,
    private val authRepository: AuthRepository
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun setup() {
        activity.lifecycleScope.launchWhenCreated {
            authRepository.isLoggedIn.collect {loggedIn->
                val navView = navControllerProvider.getNavView()
                val navController = navControllerProvider.getNavController()
                val weakNavView = WeakReference(navView)
                navController.addOnDestinationChangedListener(DestinationChangedListener(weakNavView))

                val graph = navController.navInflater.inflate(R.navigation.main_navigation)
                graph.startDestination = when {
                    loggedIn -> {
                        R.id.navigation_farmers
                    }
                    else -> {
                        R.id.login_navigation
                    }
                }
                navController.graph = graph
            }
        }
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