package com.kryptkode.farmz.screens.editfarmer.editcontactdetails.view

import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.farmers.model.FarmerView

abstract class EditContactDetailsView : BaseObservableViewMvc<EditContactDetailsView.Listener>() {

    interface Listener {
        fun onSave(farmer: FarmerView)
        fun onBackClick()
    }


    abstract fun bindFarmer(farmer: FarmerView)
    abstract fun showMobileError(message: String)
    abstract fun showEmailError(message: String)

    abstract fun clearErrors()

    abstract fun showLoading()
    abstract fun hideLoading()
}