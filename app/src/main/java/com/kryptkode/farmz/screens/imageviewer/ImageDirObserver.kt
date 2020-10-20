package com.kryptkode.farmz.screens.imageviewer

import android.os.FileObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber

import java.io.File

class ImageDirObserver (root: String, private val listener: ImageDirListener? = null) : FileObserver(
    root,
    mask
), LifecycleObserver {
    /**
     * should be end with File.separator
     */
    private val rootPath: String

    init {
        var root = root

        if (!root.endsWith(File.separator)) {
            root += File.separator
        }
        rootPath = root
        Timber.d("Constructor")
    }

    override fun onEvent(event: Int, path: String?) {
        Timber.d("Created file: $path")
        when (event) {
            CREATE, MODIFY-> {
                Timber.d("Created file: $path")
                listener?.onCreate(path)
            }
            DELETE -> {
                Timber.d("Deleted file: $path")
                listener?.onDelete(path)
            }
            DELETE_SELF -> {
                Timber.d("Deleted self: $path")
            }
            else -> {
                // just ignore
                Timber.d("Other event $event")
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun start(){
        Timber.d("Starting file observer...")
        startWatching()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stop(){
        Timber.d("Stopping file observer...")
        stopWatching()
    }

    fun getAbsolutePath(path: String?): String {
        return "$rootPath/$path"
    }

    companion object {
        private val mask = CREATE or
                DELETE or
                MODIFY or
                DELETE_SELF
    }

    interface ImageDirListener{
        fun onCreate(path: String?)
        fun onDelete(path: String?)
    }
}