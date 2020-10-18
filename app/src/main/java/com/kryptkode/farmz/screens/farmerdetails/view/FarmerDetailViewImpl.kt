package com.kryptkode.farmz.screens.farmerdetails.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.utils.PhoneNumberSanitizer
import com.kryptkode.farmz.app.utils.date.ServerDisplayedDateConverter
import com.kryptkode.farmz.databinding.LayoutFarmerDetailBinding
import com.kryptkode.farmz.screens.common.imageloader.ImageLoader
import com.kryptkode.farmz.screens.farmers.model.FarmerView

@SuppressLint("DefaultLocale")
class FarmerDetailViewImpl(
    private val imageLoader: ImageLoader,
    private val phoneNumberSanitizer: PhoneNumberSanitizer,
    private val serverDisplayedDateConverter: ServerDisplayedDateConverter,
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : FarmerDetailView() {

    private val binding = LayoutFarmerDetailBinding.inflate(layoutInflater, parent, false)


    init {

        binding.cardPersonalDetails.imagePic.setOnClickListener {
            onEachListener {
                it.onClickPic()
            }
        }

        binding.cardPersonalDetails.imgEdit.setOnClickListener {
            onEachListener {
                it.onEditPersonalDetails()
            }
        }

        binding.cardAddress.imgEdit.setOnClickListener {
            onEachListener {
                it.onEditAddress()
            }
        }

        binding.cardContact.imgEdit.setOnClickListener {
            onEachListener {
                it.onEditContactDetails()
            }
        }

        binding.cardIdentification.imgEdit.setOnClickListener {
            onEachListener {
                it.onEditIdentification()
            }
        }
    }

    override fun bindFarmer(farmer: FarmerView) {
        bindPersonalDetails(farmer)
        bindContactDetails(farmer)
        bindAddress(farmer)
        bindIdentification(farmer)
    }


    private fun bindPersonalDetails(farmer: FarmerView) {
        binding.cardPersonalDetails.tvFullName.text = joinNames(farmer)
        binding.cardPersonalDetails.tvDob.text = getString(
            R.string.farmer_dob,
            getDisplayedDate(farmer.dateOfBirth)
        )
        binding.cardPersonalDetails.tvGender.text = farmer.gender.capitalize()
        binding.cardPersonalDetails.tvOccupation.text = farmer.occupation.capitalize()
        binding.cardPersonalDetails.tvNationality.text = farmer.nationality.capitalize()
        binding.cardPersonalDetails.tvMaritalStatus.text = getMaritalStatus(farmer)

        imageLoader.load(farmer.passportPhoto, binding.cardPersonalDetails.imagePic)
    }

    private fun getDisplayedDate(date: String): String {
        return serverDisplayedDateConverter.convertServerDateToDisplayedDate(date)
    }

    private fun joinNames(farmer: FarmerView): String {
        return if (farmer.middleName.isNotEmpty()) {
            getString(
                R.string.farmer_full_name_no_middle_name,
                farmer.firstName.capitalize(),
                farmer.surname.capitalize()
            )
        } else {
            getString(
                R.string.farmer_full_name,
                farmer.firstName.capitalize(),
                farmer.middleName.capitalize(),
                farmer.surname.capitalize()
            )
        }
    }

    private fun getMaritalStatus(farmer: FarmerView): String? {
        return if (farmer.maritalStatus.equals("married", true) && farmer.spouseName.isNotEmpty()) {
            getString(
                R.string.farmer_marital_status,
                farmer.maritalStatus.capitalize(),
                farmer.spouseName.capitalize()
            )
        } else {
            farmer.maritalStatus.capitalize()
        }
    }


    private fun bindAddress(farmer: FarmerView) {
        binding.cardAddress.tvAddress.text = farmer.address.capitalize()
        binding.cardAddress.tvRegion.text = joinRegion(farmer)
    }

    private fun joinRegion(farmer: FarmerView): String {
        return farmer.city.capitalize().plus(", ").plus(farmer.lga.capitalize()).plus(", ")
            .plus(farmer.state.capitalize())
    }

    private fun bindContactDetails(farmer: FarmerView) {
        binding.cardContact.tvEmail.text = farmer.emailAddress.toLowerCase()
        binding.cardContact.tvPhone.text =
            phoneNumberSanitizer.formatWithCountryCode(farmer.mobileNumber)
    }

    private fun bindIdentification(farmer: FarmerView) {
        imageLoader.load(farmer.idImage, binding.cardIdentification.imageId)
        binding.cardIdentification.tvIdType.text = farmer.idType.capitalize()
        binding.cardIdentification.tvIdNum.text = farmer.idNumber.capitalize()
        binding.cardIdentification.tvIssueDate.text = getDisplayedDate(farmer.issueDate)
        binding.cardIdentification.tvRegNo.text = farmer.registrationNumber
        binding.cardIdentification.tvBvn.text = farmer.bvn
    }

    override val rootView: View
        get() = binding.root
}