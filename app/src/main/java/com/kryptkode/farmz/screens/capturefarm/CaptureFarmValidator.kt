package com.kryptkode.farmz.screens.capturefarm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.utils.livedata.event.Event
import com.kryptkode.farmz.app.utils.livedata.extension.asLiveData
import com.kryptkode.farmz.screens.capturefarm.model.UiFarm
import com.kryptkode.farmz.screens.capturefarm.model.UiFarmLocation
import javax.inject.Inject

/**
 * Created by kryptkode on 11/24/2019.
 */
class CaptureFarmValidator @Inject constructor(private val context: Context) {

    private val mutableFarmNameError = MutableLiveData<Event<String>>()
    val farmNameError = mutableFarmNameError.asLiveData()

    private val mutableFarmLocationError = MutableLiveData<Event<String>>()
    val farmLocationError = mutableFarmLocationError.asLiveData()

    private val mutableFarmCoordinatesError = MutableLiveData<Event<String>>()
    val farmCoordinatesError = mutableFarmCoordinatesError.asLiveData()


    fun validateFarmName(uiFarm: UiFarm): Boolean {
//        Hack to ensure all fields are validated at once
        var result = validateFarmName(uiFarm.name, mutableFarmNameError)
        result = validateFarmLocation(uiFarm.location, mutableFarmLocationError) || result
        result =
            validateFarmCoordinates(uiFarm.farmCoordinates, mutableFarmCoordinatesError) || result
        return result &&
                validateFarmName(uiFarm.name, mutableFarmNameError) &&
                validateFarmLocation(uiFarm.location, mutableFarmLocationError) &&
                validateFarmCoordinates(uiFarm.farmCoordinates, mutableFarmCoordinatesError)
    }


    private fun validateFarmName(
        name: String?,
        nameError: MutableLiveData<Event<String>>
    ): Boolean {
        if (name.isNullOrEmpty()) {
            nameError.postValue(Event(context.getString(R.string.capture_farm_name_required_message)))
            return false
        }

        return true
    }

    private fun validateFarmLocation(
        name: String?,
        nameError: MutableLiveData<Event<String>>
    ): Boolean {
        if (name.isNullOrEmpty()) {
            nameError.postValue(Event(context.getString(R.string.capture_farm_location_required_message)))
            return false
        }

        return true
    }

    private fun validateFarmCoordinates(
        list: List<UiFarmLocation>,
        errorLiveData: MutableLiveData<Event<String>>
    ): Boolean {
        if (list.isEmpty()) {
            errorLiveData.postValue(Event(context.getString(R.string.capture_farm_coordinates_required_message)))
            return false
        }

        return true
    }

    private fun validateState(
        string: String?,
        errorLiveData: MutableLiveData<Event<String>>
    ): Boolean {
        if (string.isNullOrEmpty()) {
            errorLiveData.postValue(Event(context.getString(R.string.details_state_required_message)))
            return false
        }

        return true
    }

}
