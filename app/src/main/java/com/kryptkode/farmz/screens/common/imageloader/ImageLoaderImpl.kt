package com.kryptkode.farmz.screens.common.imageloader

import android.widget.ImageView
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.customviews.PlaceHolderDrawable
import com.kryptkode.farmz.app.utils.GlideApp

class ImageLoaderImpl : ImageLoader {

    override fun load(imageSource: String, target: ImageView) {
     val placeholderDrawable = PlaceHolderDrawable(target.context)

        GlideApp.with(target)
            .load(imageSource)
            .placeholder(placeholderDrawable)
            .error(R.drawable.ic_person)
            .into(target)

    }
}