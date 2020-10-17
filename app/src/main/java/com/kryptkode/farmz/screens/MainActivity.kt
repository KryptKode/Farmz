package com.kryptkode.farmz.screens

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kryptkode.farmz.R
import com.kryptkode.farmz.navigation.MainNavigator
import com.kryptkode.farmz.navigation.NavControllerProvider
import com.kryptkode.farmz.screens.common.activity.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), NavControllerProvider {

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        controllerComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(mainNavigator)
    }

    override fun getNavController(): NavController {
        return (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment)!!.navController
    }

    override fun getNavView(): View? {
        val bottomNavView: BottomNavigationView = findViewById(R.id.nav_view)
        bottomNavView.setupWithNavController(getNavController())
        return bottomNavView
    }
}