package com.kryptkode.farmz.screens.imageviewer

import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SystemUIHelper(
    private val activity: AppCompatActivity
) {

    fun hideSystemUI(toggleActionBarVisibility: Boolean = false) {
        if (toggleActionBarVisibility && activity.supportActionBar != null) {
            activity.supportActionBar!!.hide()
        }
        /*        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);////////MAGICAL LINE*/

        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE
    }

    fun showSystemUi(toggleActionBarVisibility: Boolean = false) {
        if (toggleActionBarVisibility && activity.supportActionBar != null) {
            activity.supportActionBar!!.show()
        }
        val decorView = activity.window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)


    }

}