package com.kryptkode.farmz.app.di.controller.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kryptkode.farmz.app.di.controller.ControllerScope
import com.kryptkode.farmz.screens.dashboard.DashboardViewModel
import com.kryptkode.farmz.screens.editfarmer.EditFarmerViewModel
import com.kryptkode.farmz.screens.farm.FarmViewModel
import com.kryptkode.farmz.screens.farmerdetails.FarmerDetailViewModel
import com.kryptkode.farmz.screens.farmers.FarmersViewModel
import com.kryptkode.farmz.screens.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @ControllerScope
    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(FarmersViewModel::class)
    abstract fun bindFarmersViewModel(viewModel: FarmersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FarmerDetailViewModel::class)
    abstract fun bindFarmerDetailViewModel(viewModel: FarmerDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditFarmerViewModel::class)
    abstract fun bindEditPersonalDetailsViewModel(viewModel: EditFarmerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FarmViewModel::class)
    abstract fun bindCaptureFarmViewModel(viewModel: FarmViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel

}