package com.kryptkode.farmz.navigation.home

import com.kryptkode.farmz.navigation.NavControllerProvider
import com.kryptkode.farmz.screens.farmers.FarmersFragmentDirections
import javax.inject.Inject

class HomeNavigatorImpl @Inject constructor(
    private val navControllerProvider: NavControllerProvider
) : HomeNavigator {

    override fun navigateUp() {
        navControllerProvider.getNavController().navigateUp()
    }

    override fun farmersListToFarmersDetail(farmerId: String) {
        navControllerProvider.getNavController().navigate(
            FarmersFragmentDirections.actionNavigationFarmersToFarmerDetailFragment(farmerId)
        )
    }

    override fun detailsToPic(farmerId: String) {
        
    }

    override fun detailsToEditAddress(farmerId: String) {
        
    }

    override fun detailsToEditIdentification(farmerId: String) {
        
    }

    override fun detailsToEditContactDetails(farmerId: String) {
        
    }

    override fun detailsToEditPersonalDetails(farmerId: String) {
        
    }
}