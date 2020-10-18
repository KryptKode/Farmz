package com.kryptkode.farmz.screens.editfarmer.editaddress

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.utils.livedata.event.Event
import com.kryptkode.farmz.app.utils.livedata.extension.asLiveData
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import javax.inject.Inject

/**
 * Created by kryptkode on 11/24/2019.
 */
class AddressValidator @Inject constructor(private val context: Context) {

    private val mutableAddressError = MutableLiveData<Event<String>>()
    val addressError = mutableAddressError.asLiveData()

    private val mutableCityError = MutableLiveData<Event<String>>()
    val cityError = mutableCityError.asLiveData()

    private val mutableLgaError = MutableLiveData<Event<String>>()
    val lgaError = mutableLgaError.asLiveData()

    private val mutableStateError = MutableLiveData<Event<String>>()
    val stateError = mutableStateError.asLiveData()


    fun validateAddress(farmerView: FarmerView): Boolean {
//        Hack to ensure all fields are validated at once
        var result = validateAddress(farmerView.address, mutableAddressError)
        result = validateCity(farmerView.city, mutableCityError) || result
        result = validateLga(farmerView.dateOfBirth, mutableLgaError) || result
        result = validateState(farmerView.state, mutableStateError) || result
        return result &&
                validateAddress(farmerView.address, mutableAddressError) &&
                validateCity(farmerView.city, mutableCityError) &&
                validateLga(farmerView.dateOfBirth, mutableLgaError) &&
                validateState(farmerView.state, mutableStateError)
    }


    private fun validateAddress(
        name: String?,
        nameError: MutableLiveData<Event<String>>
    ): Boolean {
        if (name.isNullOrEmpty()) {
            nameError.postValue(Event(context.getString(R.string.details_address_required_message)))
            return false
        }

        return true
    }

    private fun validateCity(
        name: String?,
        nameError: MutableLiveData<Event<String>>
    ): Boolean {
        if (name.isNullOrEmpty()) {
            nameError.postValue(Event(context.getString(R.string.details_city_required_message)))
            return false
        }

        return true
    }

    private fun validateLga(
        string: String?,
        errorLiveData: MutableLiveData<Event<String>>
    ): Boolean {
        if (string.isNullOrEmpty()) {
            errorLiveData.postValue(Event(context.getString(R.string.details_lga_required_message)))
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
