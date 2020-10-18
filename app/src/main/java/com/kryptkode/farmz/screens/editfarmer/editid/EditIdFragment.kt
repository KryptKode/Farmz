package com.kryptkode.farmz.screens.editfarmer.editid

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.logger.Logger
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.app.utils.date.DisplayedDateFormatter
import com.kryptkode.farmz.app.utils.livedata.extension.observeEvent
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.common.dialog.DialogEventBus
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import com.kryptkode.farmz.screens.datedialog.DateSelectedEvent
import com.kryptkode.farmz.screens.editfarmer.EditFarmerViewModel
import com.kryptkode.farmz.screens.editfarmer.editid.view.EditIdView
import com.kryptkode.farmz.screens.editfarmer.editpersonaldetails.EditPersonalDetailsFragmentArgs
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import javax.inject.Inject

class EditIdFragment : BaseFragment(), EditIdView.Listener, DialogEventBus.Listener {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var toastHelper: ToastHelper

    @Inject
    lateinit var homeNavigator: HomeNavigator

    @Inject
    lateinit var viewFactory: ViewFactory

    @Inject
    lateinit var validator: IdValidator

    @Inject
    lateinit var dialogEventBus: DialogEventBus

    @Inject
    lateinit var displayedDateFormatter: DisplayedDateFormatter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: EditFarmerViewModel by viewModels { viewModelFactory }

    private val args by navArgs<EditPersonalDetailsFragmentArgs>()

    private lateinit var viewMvc: EditIdView

    private var lastSelectedDateType: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lastSelectedDateType = savedInstanceState?.getString(SELECTED_DATE_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMvc = viewFactory.getEditIdView(container)
        initIdTypeData()
        setupObservers()
        viewModel.getFarmer(args.farmerId)
        return viewMvc.rootView
    }

    private fun initIdTypeData() {
        val genders = resources.getStringArray(R.array.data_id_types).toList()
        viewMvc.bindIdTypeItems(genders)
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        dialogEventBus.registerListener(this)
    }

    private fun setupObservers() {

        validator.idTypeError.observeEvent(viewLifecycleOwner) {
            viewMvc.showIdTypeError(it)
        }

        validator.idNumberError.observeEvent(viewLifecycleOwner) {
            viewMvc.showIdNumberError(it)
        }

        validator.expiryDateError.observeEvent(viewLifecycleOwner) {
            viewMvc.showIdExpiryDateError(it)
        }

        validator.issuedDateError.observeEvent(viewLifecycleOwner) {
            viewMvc.showIdIssueDateError(it)
        }

        validator.regNumberError.observeEvent(viewLifecycleOwner) {
            viewMvc.showRegNumError(it)
        }

        validator.bvnError.observeEvent(viewLifecycleOwner) {
            viewMvc.showBvnError(it)
        }

        viewModel.showLoading.observeEvent(viewLifecycleOwner) {
            viewMvc.showLoading()
        }

        viewModel.hideLoading.observeEvent(viewLifecycleOwner) {
            viewMvc.hideLoading()
        }

        viewModel.goToNext.observeEvent(viewLifecycleOwner) {
            homeNavigator.navigateUp()
        }

        viewModel.showErrorMessage.observeEvent(viewLifecycleOwner) {
            toastHelper.showMessage(it)
        }

        viewModel.farmer.observe(viewLifecycleOwner) {
            viewMvc.bindFarmer(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SELECTED_DATE_KEY, lastSelectedDateType)
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
        dialogEventBus.unregisterListener(this)
    }

    override fun onChangeIdPic() {
        homeNavigator.toImageViewer("", "")
    }

    override fun onChooseExpiryDate(date: String) {
        lastSelectedDateType = EXPIRY_DATE
        homeNavigator.toDatePicker(displayedDateFormatter.parseDisplayedDate(date))
    }

    override fun onChooseIssueDate(date: String) {
        lastSelectedDateType = ISSUE_DATE
        homeNavigator.toDatePicker(displayedDateFormatter.parseDisplayedDate(date))
    }

    override fun onSave(farmer: FarmerView) {
        viewMvc.clearErrors()
        if (validator.validatePersonalDetails(farmer)) {
            viewModel.save(farmer)
        }
    }

    override fun onBackClick() {
        homeNavigator.navigateUp()
    }

    override fun onDialogEvent(event: Any?) {
        when (event) {
            is DateSelectedEvent -> {
                val date = event.date
                when (lastSelectedDateType) {
                    ISSUE_DATE -> viewMvc.onIssueDateSelected(
                        displayedDateFormatter.formatToDisplayedDate(
                            date
                        )
                    )
                    EXPIRY_DATE -> viewMvc.onExpiryDateSelected(
                        displayedDateFormatter.formatToDisplayedDate(
                            date
                        )
                    )
                }

            }
        }

        lastSelectedDateType = null
    }

    companion object {
        private const val ISSUE_DATE = "ISSUE_DATE"
        private const val EXPIRY_DATE = "EXPIRY_DATE"
        private const val SELECTED_DATE_KEY = "SELECTED_DATE_KEY"
    }

}