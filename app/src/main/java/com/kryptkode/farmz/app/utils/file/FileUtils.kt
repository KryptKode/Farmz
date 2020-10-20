@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.kryptkode.farmz.app.utils.file

import android.content.Context
import android.net.Uri
import id.zelory.compressor.Compressor
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
Created by kryptkode on 8/5/2019
 */

class FileUtils @Inject constructor(private val context: Context) {

    @Throws(IOException::class)
    fun createImageFile(fileNamePrefix: String= FILE_NAME_PREFIX): File {
        // Create an image file name
        val file = File.createTempFile(
            "${fileNamePrefix}_${createTempName()}_", /* prefix */
            ".$IMAGE_EXT", /* suffix */
            provideRootDir(fileNamePrefix) /* directory */
        )

        Timber.d("Created temp file: ${file.path}")
        return file
    }

    private fun createTempName(): String {
        return SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    }

    private fun provideRootDir(fileNamePrefix: String): File {
        val storageDir = File(context.filesDir, fileNamePrefix)
        if (!storageDir.exists()) {
            Timber.d("Creating directory for $fileNamePrefix")
            storageDir.mkdirs()
        }
        return storageDir
    }

    @Throws(IOException::class, SecurityException::class)
    fun provideImageFiles(rootDir: File): List<File> {
        return rootDir.listFiles { dir, name ->
            isValidImageFile(File(dir, name))
        }.toList()
    }


    @Throws(SecurityException::class)
    fun isValidImageFile(file: File): Boolean {
        val fileSize = file.length()
        Timber.d("ImageSize: $fileSize")
        return file.extension == IMAGE_EXT && file.isFile && fileSize > VALID_IMAGE_SIZE_THRESHOLD
    }

    fun getFileUri(file: File): String {
        return Uri.fromFile(file).toString()
    }

    @Throws(SecurityException::class)
    fun deleteFile(file: File): Boolean {
        return file.delete()
    }

    fun getFileName(imageFile: File): String {
        return imageFile.name
    }

    fun getRootPath(fileNamePrefix: String = FILE_NAME_PREFIX): String {
        val file = provideRootDir(fileNamePrefix)
        return file.absolutePath
    }

    fun getRootPathCompressed(fileNamePrefix: String = FILE_NAME_PREFIX): String {
        val file = provideRootDir("${fileNamePrefix}_compressed")
        if (!file.exists()) {
            file.mkdirs()
        }
        return file.absolutePath
    }

    fun compressFile(imageFile: File, fileNamePrefix: String = FILE_NAME_PREFIX): File? {
        Timber.d("About to compress file: ${imageFile.absolutePath}")
        return Compressor(context)
            .setDestinationDirectoryPath(getRootPathCompressed(fileNamePrefix))
            .compressToFile(imageFile)

    }

    companion object {
        const val IMAGE_EXT = "jpg"
        const val FILE_NAME_PREFIX = "image"
        const val VALID_IMAGE_SIZE_THRESHOLD = 1000L
    }
}