package com.kryptkode.farmz.screens.editfarmer.editcontactdetails

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.utils.livedata.event.Event
import com.kryptkode.farmz.app.utils.livedata.extension.asLiveData
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import javax.inject.Inject

/**
 * Created by kryptkode on 11/24/2019.
 */
class ContactDetailsValidator @Inject constructor(private val context: Context) {

    private val mutableMobileError = MutableLiveData<Event<String>>()
    val mobileNumberError = mutableMobileError.asLiveData()

    private val mutableEmailError = MutableLiveData<Event<String>>()
    val emailAddressError = mutableEmailError.asLiveData()


    fun validateContact(farmerView: FarmerView): Boolean {
//        Hack to ensure all fields are validated at once
        var result = validateContact(farmerView.mobileNumber, mutableMobileError)
        result = validateEmail(farmerView.emailAddress, mutableEmailError) || result

        return result &&
                validateContact(farmerView.mobileNumber, mutableMobileError) &&
                validateEmail(farmerView.emailAddress, mutableEmailError)

    }


    private fun validateContact(
        name: String?,
        nameError: MutableLiveData<Event<String>>
    ): Boolean {
        if (name.isNullOrEmpty()) {
            nameError.postValue(Event(context.getString(R.string.details_mobile_number_required_message)))
            return false
        }


        return true
    }

    private fun validateEmail(
        email: String?,
        emailError: MutableLiveData<Event<String>>
    ): Boolean {
        if (email.isNullOrEmpty()) {
            emailError.postValue(Event(context.getString(R.string.details_email_address_required_message)))
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError.postValue(Event(context.getString(R.string.validation_valid_email_required_message)))
            return false
        }

        return true
    }
}
