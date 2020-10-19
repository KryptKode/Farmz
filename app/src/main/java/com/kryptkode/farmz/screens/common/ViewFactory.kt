package com.kryptkode.farmz.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kryptkode.farmz.screens.capturefarm.view.CaptureFarmView
import com.kryptkode.farmz.screens.capturefarm.view.CaptureFarmViewImpl
import com.kryptkode.farmz.screens.common.imageloader.ImageLoader
import com.kryptkode.farmz.screens.editfarmer.editaddress.view.EditAddressView
import com.kryptkode.farmz.screens.editfarmer.editaddress.view.EditAddressViewImpl
import com.kryptkode.farmz.screens.editfarmer.editcontactdetails.view.EditContactDetailsView
import com.kryptkode.farmz.screens.editfarmer.editcontactdetails.view.EditContactDetailsViewImpl
import com.kryptkode.farmz.screens.editfarmer.editid.view.EditIdView
import com.kryptkode.farmz.screens.editfarmer.editid.view.EditIdViewImpl
import com.kryptkode.farmz.screens.editfarmer.editpersonaldetails.view.EditPersonalDetailsView
import com.kryptkode.farmz.screens.editfarmer.editpersonaldetails.view.EditPersonalDetailsViewImpl
import com.kryptkode.farmz.screens.farmerdetails.view.FarmerDetailView
import com.kryptkode.farmz.screens.farmerdetails.view.FarmerDetailViewImpl
import com.kryptkode.farmz.screens.farmers.view.*
import com.kryptkode.farmz.screens.farmlist.itemview.FarmListItemView
import com.kryptkode.farmz.screens.farmlist.itemview.FarmListItemViewImpl
import com.kryptkode.farmz.screens.farmlist.view.FarmListViewMvc
import com.kryptkode.farmz.screens.farmlist.view.FarmListViewMvcImpl
import com.kryptkode.farmz.screens.login.view.LoginView
import com.kryptkode.farmz.screens.login.view.LoginViewImpl

class ViewFactory(
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
            layoutInflater,
            parent
        )
    }

    fun getEditPersonalDetailsView(parent: ViewGroup? = null): EditPersonalDetailsView {
        return EditPersonalDetailsViewImpl(
            imageLoader,
            layoutInflater,
            parent
        )
    }

    fun getEditAddressView(parent: ViewGroup? = null): EditAddressView {
        return EditAddressViewImpl(
            layoutInflater,
            parent
        )
    }

    fun getEditContactView(parent: ViewGroup? = null): EditContactDetailsView {
        return EditContactDetailsViewImpl(
            layoutInflater,
            parent
        )
    }

    fun getEditIdView(parent: ViewGroup? = null): EditIdView {
        return EditIdViewImpl(
            imageLoader,
            layoutInflater,
            parent
        )
    }

    fun getCaptureView(parent: ViewGroup? = null): CaptureFarmView {
        return CaptureFarmViewImpl(
            layoutInflater,
            parent
        )
    }

    fun getFarmItemView(parent: ViewGroup? = null): FarmListItemView {
        return FarmListItemViewImpl(
            layoutInflater,
            parent
        )
    }

    fun getFarmListView(parent: ViewGroup? = null): FarmListViewMvc {
        return FarmListViewMvcImpl(
            this,
            layoutInflater,
            parent
        )
    }
}