package com.example.galleryvaulto

import android.content.Context
import android.provider.MediaStore

class GetImagesFoldersAndTheirPaths(private val context: Context) {

    fun getImageDirectories(context : Context): List<Pair<String, String>> {
        val imageDirectories = mutableListOf<Pair<String, String>>()
        val projection = arrayOf(
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
        )

        // Query external storage
        val externalCursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            "${MediaStore.Images.Media.DATE_MODIFIED} DESC"
        )

        externalCursor?.use {
            val dataColumnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val nameColumnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            while (it.moveToNext()) {
                val imagePath = it.getString(dataColumnIndex)
                val imageDirectory = imagePath.substringBeforeLast("/")
                val directoryName = it.getString(nameColumnIndex)
                if (!imageDirectories.any { pair -> pair.first == imageDirectory }) {
                    imageDirectories.add(Pair(imageDirectory, directoryName))
                }
            }
        }

        // Query internal storage
        val internalCursor = context.contentResolver.query(
            MediaStore.Images.Media.INTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            "${MediaStore.Images.Media.DATE_MODIFIED} DESC"
        )

        internalCursor?.use {
            val dataColumnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val nameColumnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            while (it.moveToNext()) {
                val imagePath = it.getString(dataColumnIndex)
                val imageDirectory = imagePath.substringBeforeLast("/")
                val directoryName = it.getString(nameColumnIndex)
                if (!imageDirectories.any { pair -> pair.first == imageDirectory }) {
                    imageDirectories.add(Pair(imageDirectory, directoryName))
                }
            }
        }

        return imageDirectories
    }

}