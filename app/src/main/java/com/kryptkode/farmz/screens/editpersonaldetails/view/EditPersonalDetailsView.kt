package com.kryptkode.farmz.screens.editpersonaldetails.view

import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.farmers.model.FarmerView

abstract class EditPersonalDetailsView : BaseObservableViewMvc<EditPersonalDetailsView.Listener>() {
    interface Listener {
        fun onSave(farmer: FarmerView)
        fun onChangePic()
    }

    abstract fun showFirstNameError(message:String)
    abstract fun showLastNameError(message:String)
    abstract fun showDobError(message:String)
    abstract fun showGenderError(message:String)
    abstract fun showOccupationError(message:String)
    abstract fun showNationalityError(message:String)
    abstract fun showMaritalStatusError(message:String)
    abstract fun clearErrors()

    abstract fun bindPersonalDetails(farmer: FarmerView)
    abstract fun bindGenderItems(genders:List<String>)
    abstract fun bindMaritalStatusItems(statuses:List<String>)
}