package com.kryptkode.farmz.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kryptkode.farmz.app.utils.PhoneNumberSanitizer
import com.kryptkode.farmz.app.utils.date.ServerDisplayedDateConverter
import com.kryptkode.farmz.screens.common.imageloader.ImageLoader
import com.kryptkode.farmz.screens.farmerdetails.view.FarmerDetailView
import com.kryptkode.farmz.screens.farmerdetails.view.FarmerDetailViewImpl
import com.kryptkode.farmz.screens.farmers.view.*
import com.kryptkode.farmz.screens.login.view.LoginView
import com.kryptkode.farmz.screens.login.view.LoginViewImpl

class ViewFactory(
    private val phoneNumberSanitizer: PhoneNumberSanitizer,
    private val serverDisplayedDateConverter: ServerDisplayedDateConverter,
    private val imageLoader: ImageLoader,
    private val layoutInflater: LayoutInflater
) {

    fun getLoginView(parent: ViewGroup? = null): LoginView {
        return LoginViewImpl(layoutInflater, parent)
    }

    fun getFarmerItemView(parent: ViewGroup? = null): FarmerListItemView {
        return FarmerListItemViewImpl(imageLoader, layoutInflater, parent)
    }

    fun getFarmerLoadingView(parent: ViewGroup? = null): FarmerLoadingView {
        return FarmerLoadingViewImpl(layoutInflater, parent)
    }

    fun getFarmerListView(parent: ViewGroup? = null): FarmerListView {
        return FarmerListViewImpl(this, layoutInflater, parent)
    }

    fun getFarmerDetailView(parent: ViewGroup? = null): FarmerDetailView {
        return FarmerDetailViewImpl(
            imageLoader,
            phoneNumberSanitizer,
            serverDisplayedDateConverter,
            layoutInflater,
            parent
        )
    }
}