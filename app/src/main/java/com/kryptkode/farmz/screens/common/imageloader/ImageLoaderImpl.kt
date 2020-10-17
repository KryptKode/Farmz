package com.kryptkode.farmz.screens.common.imageloader

import android.widget.ImageView
import coil.api.load

class ImageLoaderImpl : ImageLoader {
    override fun load(imageSource: String, target: ImageView) {
        target.load(imageSource)
    }
}