package com.kryptkode.farmz.screens.editfarmer.editid

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
class IdValidator @Inject constructor(private val context: Context) {

    private val mutableIdTypeError = MutableLiveData<Event<String>>()
    val idTypeError = mutableIdTypeError.asLiveData()

    private val mutableIdNumberError = MutableLiveData<Event<String>>()
    val idNumberError = mutableIdNumberError.asLiveData()

    private val mutableIssueDateError = MutableLiveData<Event<String>>()
    val issuedDateError = mutableIssueDateError.asLiveData()

    private val mutableExpiryDateError = MutableLiveData<Event<String>>()
    val expiryDateError = mutableExpiryDateError.asLiveData()

    private val mutableRegNumberError = MutableLiveData<Event<String>>()
    val regNumberError = mutableRegNumberError.asLiveData()

    private val mutableBvnError = MutableLiveData<Event<String>>()
    val bvnError = mutableBvnError.asLiveData()


    fun validatePersonalDetails(farmerView: FarmerView): Boolean {
//        Hack to ensure all fields are validated at once
        var result = validateIdType(farmerView.idType, mutableIdTypeError)
        result = validateIdNumber(farmerView.idNumber, mutableIdNumberError) || result
        result = validateIssueDate(farmerView.dateOfBirth, mutableIssueDateError) || result
        result = validateExpiryDate(farmerView.expiryDate, mutableExpiryDateError) || result
        result = validateRegNumber(farmerView.registrationNumber, mutableRegNumberError) || result
        result = validateBvn(farmerView.bvn, mutableBvnError) || result
        return result &&
                validateIdType(farmerView.idType, mutableIdTypeError) &&
                validateIdNumber(farmerView.idNumber, mutableIdNumberError) &&
                validateIssueDate(farmerView.dateOfBirth, mutableIssueDateError) &&
                validateRegNumber(farmerView.registrationNumber, mutableRegNumberError) &&
                validateBvn(farmerView.bvn, mutableBvnError)
    }


    private fun validateIdType(
        name: String?,
        nameError: MutableLiveData<Event<String>>
    ): Boolean {
        if (name.isNullOrEmpty()) {
            nameError.postValue(Event(context.getString(R.string.details_id_type_required_message)))
            return false
        }

        return true
    }

    private fun validateIdNumber(
        name: String?,
        nameError: MutableLiveData<Event<String>>
    ): Boolean {
        if (name.isNullOrEmpty()) {
            nameError.postValue(Event(context.getString(R.string.details_id_num_required_message)))
            return false
        }

        return true
    }

    private fun validateIssueDate(
        string: String?,
        errorLiveData: MutableLiveData<Event<String>>
    ): Boolean {
        if (string.isNullOrEmpty()) {
            errorLiveData.postValue(Event(context.getString(R.string.details_id_issue_date_required_message)))
            return false
        }

        return true
    }

    private fun validateExpiryDate(
        string: String?,
        errorLiveData: MutableLiveData<Event<String>>
    ): Boolean {
        if (string.isNullOrEmpty()) {
            errorLiveData.postValue(Event(context.getString(R.string.details_id_expiry_date_required_message)))
            return false
        }

        return true
    }


    private fun validateRegNumber(
        string: String?,
        errorLiveData: MutableLiveData<Event<String>>
    ): Boolean {
        if (string.isNullOrEmpty()) {
            errorLiveData.postValue(Event(context.getString(R.string.details_reg_no_required_message)))
            return false
        }

        return true
    }


    private fun validateBvn(
        string: String?,
        errorLiveData: MutableLiveData<Event<String>>
    ): Boolean {
        if (string.isNullOrEmpty()) {
            errorLiveData.postValue(Event(context.getString(R.string.details_bvn_required_message)))
            return false
        }

        return true
    }
}
