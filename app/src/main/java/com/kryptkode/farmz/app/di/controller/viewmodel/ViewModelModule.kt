package com.kryptkode.farmz.app.di.controller.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kryptkode.farmz.app.di.controller.ControllerScope
import com.kryptkode.farmz.screens.editpersonaldetails.EditPersonalDetailsViewModel
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
    @ViewModelKey(EditPersonalDetailsViewModel::class)
    abstract fun bindEditPersonalDetailsViewModel(viewModel: EditPersonalDetailsViewModel): ViewModel

}