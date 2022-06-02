package com.example.myapplication.drawer.askhelp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.location.Location
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAskhelpmapBinding
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.w3c.dom.Document

class AskHelpMapFragment() : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentAskhelpmapBinding? = null
    private val binding get() = _binding!!
    private lateinit var mView: MapView
    private lateinit var mMap: GoogleMap
    private lateinit var locationCallback : LocationCallback

    var latitude: Double? = null
    var longitude: Double? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAskhelpmapBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mView = root.findViewById(R.id.mapView)
        mView.onCreate(savedInstanceState)
        mView.getMapAsync(this)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    binding.textView14.text= arguments?.getString("key1")

}

    override fun onMapReady(googleMap: GoogleMap) {


//        val marker = LatLng(googleMap.latitude,googleMap.longitude)
        val marker = LatLng(37.514322572335935,127.06283102249932)
        updateLocation()
        googleMap.addMarker(MarkerOptions().position(marker).title("여기"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f))

    }

//    private fun setupMarker(document: Document): Marker {
//        val positionLatLng = LatLng(
//            Document.locationLatLng.latitude.toDouble(),
//            Document.locationLatLng.longitude.toDouble()
//        )
//
//        val MarkerOptions = MarkerOptions().apply{
//            position(positionLatLng)
//            title(Document.name)
//            snippet(Document.fullAddress)
//        }
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(positionLatLng, CAMERA_ZOOM_LEVEL))
//        return mMap.addMarker(markerOptions)
//    }

    override fun onStart() {
        super.onStart()
        mView.onStart()
    }
    override fun onStop() {
        super.onStop()
        mView.onStop()
    }
    override fun onResume() {
        super.onResume()
        mView.onResume()
    }
    override fun onPause() {
        super.onPause()
        mView.onPause()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        mView.onLowMemory()
    }
    override fun onDestroy() {
        mView.onDestroy()
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    @SuppressLint("MissingPermission")
    fun updateLocation(){
        val locationRequest = LocationRequest.create()
        locationRequest.run{
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
        }

        locationCallback = object: LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult?.let {
                    for((i,location) in it.locations.withIndex()){
                        Log.d("Location", "$i ${location.latitude}, ${location.longitude}")
                        setLastLocation(location)
                    }
                }
            }
        }

    }

    fun setLastLocation(lastLocation: Location){
        val LATLNG = LatLng(lastLocation.latitude, lastLocation.longitude)
        val markerOptions = MarkerOptions()
            .position(LATLNG)
            .title("Here")

        val cameraPosition = CameraPosition.Builder()
            .target(LATLNG)
            .zoom(15.0f)
            .build()
        mMap.clear()
        mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }



}
