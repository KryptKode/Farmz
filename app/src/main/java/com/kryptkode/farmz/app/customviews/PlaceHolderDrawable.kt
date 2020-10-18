package com.kryptkode.farmz.app.customviews

import android.content.Context
import android.graphics.PorterDuffColorFilter
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.kryptkode.farmz.R


class PlaceHolderDrawable(context: Context) : CircularProgressDrawable(context) {
    init {
        strokeWidth = 5f
        centerRadius = 30f
        colorFilter = PorterDuffColorFilter(
            ContextCompat.getColor(
                context,
                R.color.colorAccent
            ), android.graphics.PorterDuff.Mode.SRC_IN
        )
        start()
    }
}
