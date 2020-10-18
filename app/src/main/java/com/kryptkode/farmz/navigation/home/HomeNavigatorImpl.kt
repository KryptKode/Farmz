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

    override fun toPic(farmerId: String) {
        
    }

    override fun toEditAddress(farmerId: String) {
        
    }

    override fun toEditIdentification(farmerId: String) {
        
    }

    override fun toEditContactDetails(farmerId: String) {
        
    }

    override fun toEditPersonalDetails(farmerId: String) {
        
    }

    override fun toCaptureFarm(farmerId: String) {

    }
}