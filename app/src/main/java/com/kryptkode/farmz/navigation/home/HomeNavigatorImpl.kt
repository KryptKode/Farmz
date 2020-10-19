package com.kryptkode.farmz.navigation.home

import com.kryptkode.farmz.navigation.NavControllerProvider
import com.kryptkode.farmz.screens.datedialog.DateDialogDirections
import com.kryptkode.farmz.screens.farm.model.UiFarmLocation
import com.kryptkode.farmz.screens.farmerdetails.FarmerDetailFragmentDirections
import com.kryptkode.farmz.screens.farmers.FarmersFragmentDirections
import com.kryptkode.farmz.screens.imageviewer.ImageViewerFragmentDirections
import com.kryptkode.farmz.screens.infodialog.Info
import com.kryptkode.farmz.screens.infodialog.InfoDialogDirections
import com.kryptkode.farmz.screens.selectregion.SelectRegionFragmentDirections
import java.util.*
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


    override fun toImageViewer(photoUri: String, returnKey: String) {
        navControllerProvider.getNavController().navigate(
            ImageViewerFragmentDirections.actionToImageViewerFragment(
                photoUri, returnKey
            )
        )
    }

    override fun toEditAddress(farmerId: String) {
        navControllerProvider.getNavController().navigate(
            FarmerDetailFragmentDirections.actionFarmerDetailFragmentToEditAddressFragment(farmerId)
        )
    }

    override fun toEditIdentification(farmerId: String) {
        navControllerProvider.getNavController().navigate(
            FarmerDetailFragmentDirections.actionFarmerDetailFragmentToEditIdFragment(farmerId)
        )
    }

    override fun toEditContactDetails(farmerId: String) {
        navControllerProvider.getNavController().navigate(
            FarmerDetailFragmentDirections.actionFarmerDetailFragmentToEditContactDetailsFragment(
                farmerId
            )
        )
    }

    override fun toEditPersonalDetails(farmerId: String) {
        navControllerProvider.getNavController().navigate(
            FarmerDetailFragmentDirections.actionFarmerDetailFragmentToEditPersonalDetailsFragment(
                farmerId
            )
        )
    }

    override fun toCaptureFarm(farmerId: String) {
        navControllerProvider.getNavController().navigate(
            FarmerDetailFragmentDirections.actionFarmerDetailFragmentToCaptureFarmFragment(farmerId)
        )
    }

    override fun toDatePicker(parseDisplayedDate: Date?) {
        navControllerProvider.getNavController()
            .navigate(DateDialogDirections.actionToDateDialog(parseDisplayedDate))
    }

    override fun toInfoDialog(info: Info) {
        navControllerProvider.getNavController().navigate(
            InfoDialogDirections.actionToInfoDialog(info)
        )
    }

    override fun toSelectLocation(listOf: List<UiFarmLocation>, returnKey: String) {
        navControllerProvider.getNavController().navigate(
            SelectRegionFragmentDirections.actionToSelectRegionFragment(listOf.toTypedArray(), returnKey)
        )
    }

    override fun toFarmDetails(id: Int) {
        navControllerProvider.getNavController().navigate(
            FarmerDetailFragmentDirections.actionFarmerDetailFragmentToFarmDetailsFragment(id)
        )
    }
}