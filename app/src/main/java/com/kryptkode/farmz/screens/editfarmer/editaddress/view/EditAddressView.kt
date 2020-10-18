package com.kryptkode.farmz.screens.editfarmer.editaddress.view

import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.farmers.model.FarmerView

abstract class EditAddressView : BaseObservableViewMvc<EditAddressView.Listener>() {
    interface Listener {
        fun onSave(farmer: FarmerView)
        fun onBackClick()
    }

    abstract fun bindFarmer(farmer: FarmerView)
    abstract fun showAddressError(message: String)
    abstract fun showCityError(message: String)
    abstract fun showLgaError(message: String)
    abstract fun showStateError(message: String)

    abstract fun clearErrors()

    abstract fun showLoading()
    abstract fun hideLoading()
}