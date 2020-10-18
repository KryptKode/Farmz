package com.kryptkode.farmz.navigation.home

import com.kryptkode.farmz.navigation.NavControllerProvider
import com.kryptkode.farmz.screens.farmers.FarmersFragmentDirections
import javax.inject.Inject

class HomeNavigatorImpl @Inject constructor(
    private val navControllerProvider: NavControllerProvider
) : HomeNavigator {

    override fun farmersListToFarmersDetail(farmerId: String) {
        navControllerProvider.getNavController().navigate(
            FarmersFragmentDirections.actionNavigationFarmersToFarmerDetailFragment(farmerId)
        )
    }
}