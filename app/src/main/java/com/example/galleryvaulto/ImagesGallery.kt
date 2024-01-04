import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

object ImagesGallery {

    fun listOfImages(context: Context): ArrayList<String> {
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor: Cursor?
        val column_index_data: Int
        val listOfAllImages = ArrayList<String>()
        var absolutePathOfImage: String

        val projection = arrayOf(
            MediaStore.MediaColumns.DATA)

        val orderBy = MediaStore.Images.Media.DATE_TAKEN  // Updated ordering for images
        cursor = context.contentResolver.query(uri, projection, null, null, "$orderBy DESC")

        cursor?.use {
            column_index_data = it.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)

            while (it.moveToNext()) {
                absolutePathOfImage = it.getString(column_index_data)
                listOfAllImages.add(absolutePathOfImage)
            }
        }

        cursor?.close()
        return listOfAllImages
    }
}
