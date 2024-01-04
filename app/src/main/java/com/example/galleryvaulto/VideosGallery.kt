package com.example.galleryvaulto

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

object VideosGallery {

    fun listOfVideos(context: Context): ArrayList<String> {
        val uri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val cursor: Cursor?
        val column_index_data: Int
        val listOfAllVideos = ArrayList<String>()
        var absolutePathOfVideo: String

        val projection = arrayOf(
            MediaStore.MediaColumns.DATA
        )

        val orderBy = MediaStore.Images.Media.DATE_ADDED  // Updated ordering for videos
        cursor = context.contentResolver.query(uri, projection, null, null, "$orderBy DESC")

        cursor?.use {
            column_index_data = it.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)

            while (it.moveToNext()) {
                absolutePathOfVideo = it.getString(column_index_data)
                listOfAllVideos.add(absolutePathOfVideo)
            }
        }

        cursor?.close()
        return listOfAllVideos
    }
}
