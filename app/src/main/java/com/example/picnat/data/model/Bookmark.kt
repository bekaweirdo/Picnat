package com.example.picnat.data.model

import android.content.Context
import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.picnat.utils.ImageUtils

@Entity
data class Bookmark(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var placeId: String? = null,
    var name: String = "",
    var address: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var phone: String = "",
    var notes: String = ""
){

    fun setImage(image: Bitmap, context: Context){
        id?.let {
            ImageUtils.saveBitmapToFile(context,image,generateImageFilename(it))
        }
    }

    companion object{
        fun generateImageFilename(id: Long):String{
            return "bookmark$id.png"
        }
    }
}