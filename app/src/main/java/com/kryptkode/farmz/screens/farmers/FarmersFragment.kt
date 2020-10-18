package com.kryptkode.farmz.screens.farmers

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import javax.inject.Inject

class FarmersFragment : BaseFragment() {
    @Inject
    lateinit var viewFactory: ViewFactory

    @Inject
    lateinit var farmersController: FarmersController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: FarmersViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val farmerListView = viewFactory.getFarmerListView(container)
        lifecycle.addObserver(farmersController)
        farmersController.bindView(farmerListView)
        farmersController.bindLifeCycleOwner(viewLifecycleOwner)
        farmersController.bindViewModel(viewModel)
        return farmerListView.rootView
    }

}