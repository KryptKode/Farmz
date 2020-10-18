package com.kryptkode.farmz.app.di.controller

import com.kryptkode.farmz.app.di.controller.navigator.NavigatorModule
import com.kryptkode.farmz.app.di.controller.util.UtilModule
import com.kryptkode.farmz.app.di.controller.viewmodel.ViewModelModule
import com.kryptkode.farmz.screens.MainActivity
import com.kryptkode.farmz.screens.datedialog.DateDialog
import com.kryptkode.farmz.screens.editfarmer.editaddress.EditAddressFragment
import com.kryptkode.farmz.screens.editfarmer.editcontactdetails.EditContactDetailsFragment
import com.kryptkode.farmz.screens.editfarmer.editid.EditIdFragment
import com.kryptkode.farmz.screens.editfarmer.editpersonaldetails.EditPersonalDetailsFragment
import com.kryptkode.farmz.screens.farmerdetails.FarmerDetailFragment
import com.kryptkode.farmz.screens.farmers.FarmersFragment
import com.kryptkode.farmz.screens.imageviewer.ImageViewerFragment
import com.kryptkode.farmz.screens.infodialog.InfoDialog
import com.kryptkode.farmz.screens.login.LoginFragment
import com.kryptkode.farmz.screens.splash.SplashActivity
import dagger.Subcomponent

/**
 * Created by kryptkode on 5/21/2020.
 */
@ControllerScope
@Subcomponent(
    modules = [
        ControllerModule::class,
        ViewModelModule::class,
        NavigatorModule::class,
        UtilModule::class,
    ]
)
interface ControllerComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: SplashActivity)
    fun inject(loginFragment: LoginFragment)
    fun inject(farmersFragment: FarmersFragment)
    fun inject(farmerDetailFragment: FarmerDetailFragment)
    fun inject(editPersonalDetailsFragment: EditPersonalDetailsFragment)
    fun inject(dateDialog: DateDialog)
    fun inject(editAddressFragment: EditAddressFragment)
    fun inject(editContactDetailsFragment: EditContactDetailsFragment)
    fun inject(editIdFragment: EditIdFragment)
    fun inject(imageViewerFragment: ImageViewerFragment)
    fun inject(infoDialog: InfoDialog)
}