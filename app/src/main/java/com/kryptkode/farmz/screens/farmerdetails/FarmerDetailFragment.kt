package com.kryptkode.farmz.screens.farmerdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import javax.inject.Inject


class FarmerDetailFragment : BaseFragment(){

    @Inject
    lateinit var viewFactory: ViewFactory

    @Inject
    lateinit var farmersController: FarmerDetailController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FarmerDetailViewModel by viewModels { viewModelFactory }


    private val args by navArgs<FarmerDetailFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val farmerDetailView = viewFactory.getFarmerDetailView(container)
        lifecycle.addObserver(farmersController)
        farmersController.bindView(farmerDetailView)
        farmersController.bindLifeCycleOwner(viewLifecycleOwner)
        farmersController.bindViewModel(viewModel)
        farmersController.bindFarmerId(args.farmerId)
        return farmerDetailView.rootView
    }

}