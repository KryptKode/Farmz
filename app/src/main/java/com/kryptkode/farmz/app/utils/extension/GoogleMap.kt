package com.kryptkode.farmz.app.utils.extension

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.PolygonOptions
import com.kryptkode.farmz.R

fun GoogleMap.disableUserInput() {
    uiSettings.isZoomControlsEnabled = false
    uiSettings.isZoomGesturesEnabled = false
    uiSettings.isCompassEnabled = false
    uiSettings.isScrollGesturesEnabledDuringRotateOrZoom = false
    uiSettings.setAllGesturesEnabled(false)
}

fun GoogleMap.drawPolygon(context: Context, points: List<LatLng>): Polygon? {
    val polygonOptions = PolygonOptions()
    polygonOptions.fillColor(
        ContextCompat.getColor(
            context,
            R.color.colorPrimaryLight
        )
    )
    polygonOptions.strokeColor(ContextCompat.getColor(context, R.color.colorAccent))
    polygonOptions.strokeWidth(7f)
    polygonOptions.addAll(points)
    return addPolygon(polygonOptions)
}