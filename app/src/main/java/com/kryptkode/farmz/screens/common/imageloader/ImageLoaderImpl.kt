package com.kryptkode.farmz.screens.common.imageloader

import android.content.Context
import android.widget.ImageView
import coil.api.load
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.customviews.PlaceHolderDrawable

class ImageLoaderImpl (context: Context) : ImageLoader {

    override fun load(imageSource: String, target: ImageView) {
     val placeholderDrawable = PlaceHolderDrawable(target.context)
        target.load(imageSource){
            placeholder(placeholderDrawable)
            error(R.drawable.ic_person)
        }

    }
}