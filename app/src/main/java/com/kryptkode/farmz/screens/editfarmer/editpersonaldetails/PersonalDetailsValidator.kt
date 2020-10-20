package com.kryptkode.farmz.screens.editfarmer.editpersonaldetails

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
class PersonalDetailsValidator @Inject constructor(private val context: Context) {

    private val mutableFirstNameError = MutableLiveData<Event<String>>()
    val firstNameError = mutableFirstNameError.asLiveData()

    private val mutableLastNameError = MutableLiveData<Event<String>>()
    val lastNameError = mutableLastNameError.asLiveData()

    private val mutableDobError = MutableLiveData<Event<String>>()
    val dobError = mutableDobError.asLiveData()

    private val mutableGenderError = MutableLiveData<Event<String>>()
    val genderError = mutableGenderError.asLiveData()

    private val mutableNationalityError = MutableLiveData<Event<String>>()
    val nationalityError = mutableNationalityError.asLiveData()

    private val mutableOccupationError = MutableLiveData<Event<String>>()
    val occupationError = mutableOccupationError.asLiveData()

    private val mutableMaritalStatusError = MutableLiveData<Event<String>>()
    val maritalStatusError = mutableMaritalStatusError.asLiveData()


    fun validatePersonalDetails(farmerView: FarmerView): Boolean {
//        Hack to ensure all fields are validated at once
        var result = validateFirstName(farmerView.firstName, mutableFirstNameError)
        result = validateLastName(farmerView.surname, mutableLastNameError) || result
        result = validateDob(farmerView.dateOfBirth, mutableDobError) || result
        result = validateGender(farmerView.gender, mutableGenderError) || result
        result = validateNationality(farmerView.nationality, mutableNationalityError) || result
        result = validateOccupation(farmerView.occupation, mutableOccupationError) || result
        result =
            validateMaritalStatus(farmerView.maritalStatus, mutableMaritalStatusError) || result
        return result &&
                validateFirstName(farmerView.firstName, mutableFirstNameError) &&
                validateLastName(farmerView.surname, mutableLastNameError) &&
                validateDob(farmerView.dateOfBirth, mutableDobError) &&
                validateNationality(farmerView.nationality, mutableNationalityError) &&
                validateOccupation(farmerView.occupation, mutableOccupationError) &&
                validateMaritalStatus(farmerView.maritalStatus, mutableMaritalStatusError)
    }


    private fun validateFirstName(
        name: String?,
        nameError: MutableLiveData<Event<String>>
    ): Boolean {
        if (name.isNullOrEmpty()) {
            nameError.postValue(Event(context.getString(R.string.details_first_name_required_message)))
            return false
        }

        return true
    }

    private fun validateLastName(
        name: String?,
        nameError: MutableLiveData<Event<String>>
    ): Boolean {
        if (name.isNullOrEmpty()) {
            nameError.postValue(Event(context.getString(R.string.details_last_name_required_message)))
            return false
        }

        return true
    }

    private fun validateDob(
        string: String?,
        errorLiveData: MutableLiveData<Event<String>>
    ): Boolean {
        if (string.isNullOrEmpty()) {
            errorLiveData.postValue(Event(context.getString(R.string.details_dob_required_message)))
            return false
        }

        return true
    }

    private fun validateGender(
        string: String?,
        errorLiveData: MutableLiveData<Event<String>>
    ): Boolean {
        if (string.isNullOrEmpty()) {
            errorLiveData.postValue(Event(context.getString(R.string.details_gender_required_message)))
            return false
        }

        return true
    }


    private fun validateNationality(
        string: String?,
        errorLiveData: MutableLiveData<Event<String>>
    ): Boolean {
        if (string.isNullOrEmpty()) {
            errorLiveData.postValue(Event(context.getString(R.string.details_nationality_required_message)))
            return false
        }

        return true
    }

    private fun validateMaritalStatus(
        string: String?,
        errorLiveData: MutableLiveData<Event<String>>
    ): Boolean {
        if (string.isNullOrEmpty()) {
            errorLiveData.postValue(Event(context.getString(R.string.details_marital_status_required_message)))
            return false
        }

        return true
    }


    private fun validateOccupation(
        string: String?,
        errorLiveData: MutableLiveData<Event<String>>
    ): Boolean {
        if (string.isNullOrEmpty()) {
            errorLiveData.postValue(Event(context.getString(R.string.details_nationality_required_message)))
            return false
        }

        return true
    }
}
