package com.kryptkode.farmz.screens.capturefarm

import androidx.lifecycle.ViewModel
import com.kryptkode.farmz.screens.capturefarm.model.UiFarm
import com.kryptkode.farmz.screens.capturefarm.model.UiFarmLocation
import javax.inject.Inject

class CaptureFarmViewModel @Inject constructor() : ViewModel() {

    private var farmLocation = mutableListOf<UiFarmLocation>()

    fun saveFarm(uiFarm: UiFarm) {

    }

    fun setLocation(coordinates: List<UiFarmLocation>) {
        farmLocation.clear()
        farmLocation.addAll(coordinates)
    }

    fun getLocation(): List<UiFarmLocation> {
        return farmLocation
    }

}