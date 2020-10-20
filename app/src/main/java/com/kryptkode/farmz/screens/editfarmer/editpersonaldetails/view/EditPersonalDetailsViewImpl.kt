package com.kryptkode.farmz.screens.editfarmer.editpersonaldetails.view

import android.annotation.SuppressLint
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.kryptkode.farmz.app.utils.extension.beGone
import com.kryptkode.farmz.app.utils.extension.beVisible
import com.kryptkode.farmz.app.utils.extension.bindData
import com.kryptkode.farmz.app.utils.extension.setCapsInputFilter
import com.kryptkode.farmz.databinding.LayoutEditPersonalDetailsBinding
import com.kryptkode.farmz.screens.common.imageloader.ImageLoader
import com.kryptkode.farmz.screens.farmers.model.FarmerView

@SuppressLint("DefaultLocale")
class EditPersonalDetailsViewImpl(
    private val imageLoader: ImageLoader,
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : EditPersonalDetailsView() {

    private val binding = LayoutEditPersonalDetailsBinding.inflate(layoutInflater, parent, false)

    private lateinit var farmer: FarmerView

    init {

        binding.firstNameEditText.setCapsInputFilter()
        binding.lastNameEditText.setCapsInputFilter()
        binding.middleNameEditText.setCapsInputFilter()

        binding.firstNameEditText.addTextChangedListener {
            clearFirstNameError()
        }

        binding.lastNameEditText.addTextChangedListener {
            clearLastNameError()
        }

        binding.dateEditText.addTextChangedListener {
            clearDobError()
        }

        binding.genderEditText.addTextChangedListener {
            clearGenderError()
        }

        binding.occupationEditText.addTextChangedListener {
            clearOccupationError()
        }

        binding.nationalityEditText.addTextChangedListener {
            clearNationalityError()
        }

        binding.maritalStatusEditText.addTextChangedListener {
            clearMaritalStatusError()
        }

        binding.imagePic.setOnClickListener {
            onEachListener {
                it.onChangePic()
            }
        }

        binding.fabSave.setOnClickListener {
            onEachListener {
                it.onSave(
                    farmer.copy(
                        firstName = binding.firstNameEditText.text.toString(),
                        middleName = binding.middleNameEditText.text.toString(),
                        surname = binding.lastNameEditText.text.toString(),
                        dateOfBirth = binding.dateEditText.text.toString(),
                        gender = binding.genderEditText.text.toString(),
                        occupation = binding.occupationEditText.text.toString(),
                        nationality = binding.nationalityEditText.text.toString(),
                        maritalStatus = binding.maritalStatusEditText.text.toString(),
                        spouseName = binding.spouseEditText.text.toString()
                    )
                )
            }
        }

        binding.tvTitle.setOnClickListener {
            onEachListener {
                it.onBackClick()
            }
        }

        binding.dateEditText.setOnClickListener {
            onEachListener {
                it.onChooseDate(binding.dateEditText.text.toString())
            }
        }

        binding.dateEditText.inputType = InputType.TYPE_NULL
    }

    override fun bindPic(photoUri: String) {
        imageLoader.load(photoUri, binding.imagePic)
    }

    override fun bindPersonalDetails(farmer: FarmerView) {
        this.farmer = farmer
        binding.firstNameEditText.setText(farmer.firstName.capitalize())
        binding.middleNameEditText.setText(farmer.middleName.capitalize())
        binding.lastNameEditText.setText(farmer.surname.capitalize())
        binding.dateEditText.setText(farmer.dateOfBirth.capitalize())
        binding.genderEditText.setText(farmer.gender.capitalize(), false)
        binding.occupationEditText.setText(farmer.occupation.capitalize())
        binding.nationalityEditText.setText(farmer.nationality.capitalize())
        binding.maritalStatusEditText.setText(farmer.maritalStatus.capitalize(), false)
        binding.spouseEditText.setText(farmer.spouseName.capitalize())

        bindPic(farmer.passportPhoto)
    }

    override fun bindGenderItems(genders: List<String>) {
        binding.genderEditText.bindData(genders)
    }

    override fun bindMaritalStatusItems(statuses: List<String>) {
        binding.maritalStatusEditText.bindData(statuses)
    }

    override fun showFirstNameError(message: String) {
        binding.firstNameInput.error = message
    }

    override fun showLastNameError(message: String) {
        binding.lastNameInput.error = message
    }

    override fun showDobError(message: String) {
        binding.dateInput.error = message
    }

    override fun showGenderError(message: String) {
        binding.genderInput.error = message
    }

    override fun showOccupationError(message: String) {
        binding.occupationInput.error = message
    }

    override fun showNationalityError(message: String) {
        binding.nationalityInput.error = message
    }

    override fun showMaritalStatusError(message: String) {
        binding.maritalStatusInput.error = message
    }

    override fun hideLoading() {
        binding.progress.root.beGone()
        binding.fabSave.isEnabled = true
    }

    override fun showLoading() {
        binding.progress.root.beVisible()
        binding.fabSave.isEnabled = false
    }

    override fun onDateSelected(date: String) {
        binding.dateEditText.setText(date)
    }

    override fun clearErrors() {
        clearFirstNameError()
        clearLastNameError()
        clearDobError()
        clearGenderError()
        clearOccupationError()
        clearNationalityError()
        clearMaritalStatusError()
    }

    private fun clearFirstNameError() {
        binding.firstNameInput.error = null
    }

    private fun clearLastNameError() {
        binding.lastNameInput.error = null
    }

    private fun clearDobError() {
        binding.dateInput.error = null
    }

    private fun clearGenderError() {
        binding.genderInput.error = null
    }

    private fun clearOccupationError() {
        binding.occupationInput.error = null
    }

    private fun clearNationalityError() {
        binding.nationalityInput.error = null
    }

    private fun clearMaritalStatusError() {
        binding.maritalStatusInput.error = null
    }

    override val rootView: View
        get() = binding.root

}