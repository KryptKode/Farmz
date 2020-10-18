package com.kryptkode.farmz.navigation.home

interface HomeNavigator {
    fun navigateUp()
    fun farmersListToFarmersDetail(farmerId:String)
    fun toPic(farmerId: String)
    fun toEditAddress(farmerId: String)
    fun toEditIdentification(farmerId: String)
    fun toEditContactDetails(farmerId: String)
    fun toEditPersonalDetails(farmerId: String)
    fun toCaptureFarm(farmerId: String)
    fun toUpdatePhoto()
}