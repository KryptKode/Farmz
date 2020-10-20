package com.kryptkode.farmz.screens.farm.farmdetails.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.kryptkode.farmz.app.utils.extension.beGone
import com.kryptkode.farmz.app.utils.extension.beVisible
import com.kryptkode.farmz.app.utils.extension.disableUserInput
import com.kryptkode.farmz.app.utils.extension.drawPolygon
import com.kryptkode.farmz.databinding.LayoutFarmDetailsBinding
import com.kryptkode.farmz.screens.farm.model.UiFarm
import com.kryptkode.farmz.screens.farm.model.UiFarmLocation

class FarmDetailsViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : FarmDetailsView() {

    private val binding = LayoutFarmDetailsBinding.inflate(layoutInflater, parent, false)

    private var points: MutableList<LatLng> = mutableListOf()

    private var googleMap: GoogleMap? = null

    init {

        initMap()

        binding.farmLocationEditText.addTextChangedListener {
            clearLocationError()
        }

        binding.farmNameEditText.addTextChangedListener {
            clearNameError()
        }

        binding.fabSave.setOnClickListener {
            onEachListener {
                it.onSave(
                    binding.farmNameEditText.text.toString(),
                    binding.farmLocationEditText.text.toString()
                )
            }
        }

        binding.tvTitle.setOnClickListener {
            onEachListener {
                it.onBackClick()
            }
        }

        binding.farmLocationCard.setOnClickListener {
            onEachListener {
                it.onAddLocation()
            }
        }
    }


    private fun initMap() {
        binding.map.getMapAsync { map ->
            googleMap = map
            map.disableUserInput()
            updatePolygon()
        }

    }

    private fun updatePolygon() {
        if (points.isNotEmpty()) {
            val latLngBounds = LatLngBounds.builder()
            points.onEach {
                latLngBounds.include(it)
            }
            googleMap?.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    latLngBounds.build().center,
                    16f
                )
            )
            googleMap?.drawPolygon(binding.root.context, points)

            googleMap?.setOnMapClickListener {
                onEachListener {
                    it.onAddLocation()
                }
            }
        }
    }

    override fun showLocationError(message: String) {
        binding.farmLocationInput.error = message
    }

    override fun showNameError(message: String) {
        binding.farmNameInput.error = message
    }

    override fun hideLoading() {
        binding.progress.root.beGone()
        binding.fabSave.isEnabled = true
    }

    override fun showLoading() {
        binding.progress.root.beVisible()
        binding.fabSave.isEnabled = false
    }

    override fun clearErrors() {
        clearLocationError()
        clearNameError()
    }

    override fun getMap(): MapView {
        return binding.map
    }

    private fun clearLocationError() {
        binding.farmLocationInput.error = null
    }

    private fun clearNameError() {
        binding.farmNameInput.error = null
    }

    override fun onSelectLocation(locations: List<UiFarmLocation>) {
        points.clear()
        points.addAll(locations.map {
            LatLng(it.latitude, it.longitude)
        })

        updatePolygon()
    }

    override fun bindFarm(uiFarm: UiFarm) {
        binding.farmLocationEditText.setText(uiFarm.location)
        binding.farmNameEditText.setText(uiFarm.name)
        onSelectLocation(uiFarm.farmCoordinates)
    }

    override val rootView: View
        get() = binding.root
}
