package com.kryptkode.farmz.navigation.home

import androidx.navigation.NavController
import com.kryptkode.farmz.navigation.NavControllerProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Before

class HomeNavigatorTest {

    private lateinit var homeNavigator: HomeNavigator

    private val navigationControllerProvider: NavControllerProvider = mockk()

    @Before
    fun setUp() {
        homeNavigator = HomeNavigatorImpl(navigationControllerProvider)
        stubNavigatorController()
    }


    private fun stubNavigatorController() {
        every {
            navigationControllerProvider.getNavController()
        }returns Any() as NavController
    }
}