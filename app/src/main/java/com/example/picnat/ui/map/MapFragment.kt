package com.example.picnat.ui.map

import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.example.picnat.R
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PointOfInterest
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.PhotoMetadata
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPhotoRequest
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var placesClient: PlacesClient

    companion object {
        private const val REQUEST_LOCATION = 1
        private const val TAG = "MapFragment"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setupLocationClient()
        setupPlacesClient()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        setupMapListeners()
        getCurrentLocation()

    }

    private fun getCurrentLocation(){
        if(context?.let { ActivityCompat.checkSelfPermission(it,android.Manifest.permission.ACCESS_FINE_LOCATION) } != PackageManager.PERMISSION_GRANTED){
            requestLocationPermissions()
        }else{
            mMap.isMyLocationEnabled = true
            fusedLocationClient.lastLocation.addOnCompleteListener {

                val location = it.result
                if(location != null){
                    val latLng = LatLng(location.latitude,location.longitude)
                    Log.d("location","${location.latitude} \t ${location.longitude}")

                    val update = CameraUpdateFactory.newLatLngZoom(latLng,16.0f)
                    mMap.moveCamera(update)
                }
                else{
                    Log.e(TAG,"No location found")
                }
            }
        }
    }

    private fun requestLocationPermissions(){
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION)
    }

    private fun setupLocationClient(){
        fusedLocationClient = context?.let { LocationServices.getFusedLocationProviderClient(it) }!!
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == REQUEST_LOCATION){
            if(grantResults.size == 1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getCurrentLocation()
            }else{
                Log.e(TAG,"Location permission denied")
            }
        }
    }
    private fun setupPlacesClient(){
        Places.initialize(activity!!.applicationContext,"AIzaSyC57L_M4umeLzg2gm1zTUFfUwD_3SGNtts")
        placesClient = context?.let { Places.createClient(it) }!!
    }

    private fun displayPoi(pointOfInterest: PointOfInterest){
        displayPoiGetPlaceStep(pointOfInterest)
    }

    // PlacesClient to fetch details about a place
    private fun displayPoiGetPlaceStep(pointOfInterest: PointOfInterest){
        val placeId = pointOfInterest.placeId

        val placeFields = listOf(Place.Field.ID,
            Place.Field.NAME,
            Place.Field.PHONE_NUMBER,
            Place.Field.PHOTO_METADATAS,
            Place.Field.ADDRESS,
            Place.Field.LAT_LNG)

        val request = FetchPlaceRequest
            .builder(placeId,placeFields)
            .build()

        placesClient.fetchPlace(request)
            .addOnSuccessListener { response ->
                val place = response.place
                    Toast.makeText(activity?.applicationContext,
                        "${place.name}, "+
                    "${place.phoneNumber}", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { exception ->
                if(exception is ApiException){
                    val statusCode = exception.statusCode
                    Log.e(TAG,"Places Not Found: " + exception.message + ", " + "statusCode: "+statusCode)
                }
            }
    }

    private fun setupMapListeners(){
        mMap.setOnPoiClickListener {
            displayPoi(it)
        }
    }

    private fun displayPoiGetPhotoStep(place: Place){
        val photoMetadata = place.photoMetadatas?.get(0)

        if(photoMetadata == null){
            displayPoiDisplayStep(place,null)
        }

        val photoRequest = FetchPhotoRequest.builder(photoMetadata as PhotoMetadata)
            .setMaxWidth(resources.getDimensionPixelSize(R.dimen.default_image_width))
            .setMaxHeight(resources.getDimensionPixelSize(R.dimen.default_image_height))
            .build()

        placesClient.fetchPhoto(photoRequest)
            .addOnSuccessListener {fetchPhotoResponse->
                val bitmap = fetchPhotoResponse.bitmap
                displayPoiDisplayStep(place,bitmap)
            }
            .addOnFailureListener {exception ->
                if(exception is ApiException){
                    val statusCode = exception.statusCode
                    Log.e(TAG,"Place not found: "+ exception.message + ", "+ "statusCod: " + statusCode)
                }
            }
    }

    private fun displayPoiDisplayStep(place: Place, photo: Bitmap?){
        val iconPhoto = if(photo == null){
            BitmapDescriptorFactory.defaultMarker()
        }else{
            BitmapDescriptorFactory.fromBitmap(photo)
        }
        mMap.addMarker(MarkerOptions()
            .position(place.latLng as LatLng)
            .icon(iconPhoto)
            .title(place.name)
            .snippet(place.phoneNumber))
    }
}
