package com.example.project01.reinoso1165606.finalexampractical

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class PlaceActivity : AppCompatActivity(), OnMapReadyCallback {

    var position: LatLng? = null
    var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_place)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // getting the data from the bundle
        val bundle = intent.extras
        val lat = bundle?.getString("lat")
        val lon = bundle?.getString("lon")
        val title = bundle?.getString("title")

        if(lat != null && lon != null){
            position = LatLng(lat.toString().toDouble(), lon.toString().toDouble())
        }
        if(title != null){
            val placeName = findViewById<TextView>(R.id.placeName)
            placeName.text = title.toString()
        }

        // starting the map
        val mapFragment = supportFragmentManager.findFragmentById(R.id.placefragmentcontainer) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0
        googleMap?.addMarker(position?.let { MarkerOptions().position(it) })
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(position))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(position,15.0f))
    }
}