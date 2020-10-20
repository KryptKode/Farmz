package com.kryptkode.farmz.screens.editfarmer.editid.view

import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.farmers.model.FarmerView

abstract class EditIdView: BaseObservableViewMvc<EditIdView.Listener>() {

    interface Listener {
        fun onChangeIdPic()
        fun onChangeFingerPrintPic()
        fun onChooseExpiryDate(date: String)
        fun onChooseIssueDate(date: String)
        fun onSave(farmer: FarmerView)
        fun onBackClick()
    }


    abstract fun bindFarmer(farmer: FarmerView)
    abstract fun showBvnError(message: String)
    abstract fun showRegNumError(message: String)
    abstract fun showIdTypeError(message: String)
    abstract fun showIdNumberError(message: String)
    abstract fun showIdExpiryDateError(message: String)
    abstract fun showIdIssueDateError(message: String)

    abstract fun bindIdTypeItems(idTypes:List<String>)

    abstract fun onIssueDateSelected(date:String)
    abstract fun onExpiryDateSelected(date:String)

    abstract fun clearErrors()

    abstract fun bindIdPhoto(photoUri: String)
    abstract fun bindFingerPrintPhoto(photoUri: String)

    abstract fun showLoading()
    abstract fun hideLoading()
}