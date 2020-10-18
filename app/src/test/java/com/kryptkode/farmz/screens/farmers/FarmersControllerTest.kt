package com.kryptkode.farmz.screens.farmers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.farmers.view.FarmerListView
import com.kryptkode.farmz.screens.login.LoginViewModel
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule

class FarmersControllerTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var controller: FarmersController

    private val homeNavigator: HomeNavigator = mockk()
    private val toastHelper: ToastHelper = mockk()
    private val loginViewModel: LoginViewModel = mockk()
    private val lifecycleOwner: LifecycleOwner = mockk()
    private val lifecycle = LifecycleRegistry(lifecycleOwner)
    private val farmerListView: FarmerListView = mockk()

    @Before
    fun setUp() {
        controller = FarmersController(homeNavigator, toastHelper)
    }


}