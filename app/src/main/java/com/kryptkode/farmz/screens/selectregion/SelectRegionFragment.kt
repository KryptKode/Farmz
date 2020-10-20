package com.kryptkode.farmz.screens.selectregion

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.logger.Logger
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.app.utils.extension.beInvisible
import com.kryptkode.farmz.app.utils.extension.beVisibleIf
import com.kryptkode.farmz.app.utils.extension.drawPolygon
import com.kryptkode.farmz.app.utils.viewbinding.viewBinding
import com.kryptkode.farmz.databinding.FragmentSelectRegionBinding
import com.kryptkode.farmz.datareturn.ScreenDataReturnBuffer
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import com.kryptkode.farmz.screens.farm.model.UiFarmLocation
import javax.inject.Inject


class SelectRegionFragment : BaseFragment(R.layout.fragment_select_region) {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var homeNavigator: HomeNavigator

    @Inject
    lateinit var toastHelper: ToastHelper

    @Inject
    lateinit var screenDataReturnBuffer: ScreenDataReturnBuffer

    private var polygon: Polygon? = null

    private val binding by viewBinding(FragmentSelectRegionBinding::bind)

    private val args by navArgs<SelectRegionFragmentArgs>()

    private var points = mutableListOf<LatLng>()
    private var markerList = mutableListOf<Marker>()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabSave.beInvisible()

        binding.fabSave.setOnClickListener {
            val uiLocations = points.map { UiFarmLocation(it.latitude, it.longitude) }
            screenDataReturnBuffer.putValue(args.returnKey, uiLocations)
            homeNavigator.navigateUp()
        }

        binding.tvTitle.setOnClickListener {
            homeNavigator.navigateUp()
        }

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment?.getMapAsync { googleMap ->
            if (args.locations.toList().isNotEmpty()) {
                points.addAll(args.locations.toList().map {
                    LatLng(it.latitude, it.longitude)
                })
                points.onEach {
                    drawMarkers(googleMap, it)
                }
                drawPolygon(googleMap)

            }
            val center = getCenter()
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, DEFAULT_ZOOM))
            googleMap.setOnMapClickListener { latLng ->

                drawMarkers(googleMap, latLng)
                points.add(latLng)
                drawPolygon(googleMap)
            }

            googleMap.setOnMarkerClickListener {
                toastHelper.showMessage("Long press marker to drag")
                true
            }

            googleMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
                override fun onMarkerDrag(marker: Marker) {

                }

                override fun onMarkerDragEnd(marker: Marker) {
                    updateMarkerLocation(googleMap, marker)
                }

                override fun onMarkerDragStart(marker: Marker) {
                    updateMarkerLocation(googleMap, marker)
                }
            })
        }
    }

    private fun drawMarkers(googleMap: GoogleMap, latLng: LatLng) {
        val icon =
            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_location_maps)
        val bitmap = BitmapDescriptorFactory.fromBitmap(getBitmapFromDrawable(icon))

        val marker = googleMap.addMarker(
            MarkerOptions().position(latLng).icon(bitmap).draggable(true)
        )
        marker.tag = latLng
        markerList.add(marker)
    }

    private fun getCenter(): LatLng {
        val defaultPoints = args.locations.toList()
        return if (defaultPoints.isEmpty()) {
            LatLng(DEFAULT_LAT, DEFAULT_LNG)
        } else {
            val latLngBounds = LatLngBounds.builder()
            for (defaultPoint in defaultPoints) {
                latLngBounds.include(LatLng(defaultPoint.latitude, defaultPoint.longitude))
            }
            latLngBounds.build().center
        }
    }

    private fun updateMarkerLocation(googleMap: GoogleMap, marker: Marker) {
        val latLng = marker.tag as LatLng?
        val position = points.indexOf(latLng)
        points[position] = marker.position
        marker.tag = marker.position
        drawPolygon(googleMap)
    }

    private fun drawPolygon(googleMap: GoogleMap) {
        polygon?.remove()
        polygon = googleMap.drawPolygon(requireContext(), points)
        binding.fabSave.beVisibleIf(points.size > 2)
    }


    private fun getBitmapFromDrawable(drawable: Drawable?): Bitmap? {
        val obm = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(obm)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return obm
    }

    companion object {
        private const val DEFAULT_LAT = 6.434853
        private const val DEFAULT_LNG = 3.434808
        const val DEFAULT_ZOOM = 16f
    }
}