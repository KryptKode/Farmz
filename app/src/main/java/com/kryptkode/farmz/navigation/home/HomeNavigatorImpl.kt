package com.kryptkode.farmz.navigation.home

import com.kryptkode.farmz.navigation.NavControllerProvider
import javax.inject.Inject

class HomeNavigatorImpl @Inject constructor(
    private val navControllerProvider: NavControllerProvider
) : HomeNavigator {

    override fun farmersListToFarmersDetail() {

    }
}