package com.example.picnat.ui.map.adapter

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.picnat.R
import com.example.picnat.data.model.PlaceInfo
import com.example.picnat.ui.map.MapFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class BookmarkInfoWindowAdapter(context: MapFragment): GoogleMap.InfoWindowAdapter {
    private val contents : View

    init {
        contents = context.layoutInflater.inflate(R.layout.content_bookmark_info,null)
    }

    override fun getInfoContents(marker: Marker?): View {
        val titleView = contents.findViewById<TextView>(R.id.title)
        if (marker != null) {
            titleView.text = marker.title ?: ""
        }

        val phoneView = contents.findViewById<TextView>(R.id.phone)
        if (marker != null) {
            phoneView.text = marker.snippet ?: ""
        }

        val imageView = contents.findViewById<ImageView>(R.id.photo)
        imageView.setImageBitmap((marker?.tag as PlaceInfo).image)
        return contents
    }

    override fun getInfoWindow(p0: Marker?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}