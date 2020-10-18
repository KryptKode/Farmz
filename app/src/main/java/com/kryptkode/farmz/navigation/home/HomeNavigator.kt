package com.kryptkode.farmz.navigation.home

interface HomeNavigator {
    fun navigateUp()
    fun farmersListToFarmersDetail(farmerId:String)
    fun detailsToPic(farmerId: String)
    fun detailsToEditAddress(farmerId: String)
    fun detailsToEditIdentification(farmerId: String)
    fun detailsToEditContactDetails(farmerId: String)
    fun detailsToEditPersonalDetails(farmerId: String)
}