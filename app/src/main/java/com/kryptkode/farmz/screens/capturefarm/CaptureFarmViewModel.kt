package com.kryptkode.farmz.screens.capturefarm

import androidx.lifecycle.ViewModel
import com.kryptkode.farmz.screens.capturefarm.model.UiFarm
import com.kryptkode.farmz.screens.capturefarm.model.UiFarmLocation
import javax.inject.Inject

class CaptureFarmViewModel @Inject constructor() : ViewModel() {

    var farmLocation = mutableListOf<UiFarmLocation>()

    fun saveFarm(uiFarm: UiFarm) {

    }

}