package com.kryptkode.farmz.navigation.home

import com.kryptkode.farmz.screens.capturefarm.model.UiFarmLocation
import com.kryptkode.farmz.screens.infodialog.Info
import java.util.*

interface HomeNavigator {
    fun navigateUp()
    fun farmersListToFarmersDetail(farmerId:String)
    fun toImageViewer(photoUri:String, returnKey:String)
    fun toEditAddress(farmerId: String)
    fun toEditIdentification(farmerId: String)
    fun toEditContactDetails(farmerId: String)
    fun toEditPersonalDetails(farmerId: String)
    fun toCaptureFarm(farmerId: String)
    fun toDatePicker(parseDisplayedDate: Date?)
    fun toInfoDialog(info: Info)
    fun toSelectLocation(listOf: List<UiFarmLocation>, returnKey: String)
}