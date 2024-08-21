package com.example.project01.reinoso1165606.finalexampractical

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private var googleMap: GoogleMap? = null
    var latLonData:LatLng? = null
    var database = Database(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // starting the map
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragmentcontainer) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // setting the add button behaviour
        val addBtn = findViewById<Button>(R.id.addBtn)
        val input = findViewById<EditText>(R.id.enterNameInput)

        addBtn.setOnClickListener {
            val title = input.text.toString().trim()
            val position = PositionDataModel(title, latLonData?.latitude.toString(), latLonData?.longitude.toString())
            database.insert(position)
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        // check and/or request permission
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                100
            )
        } else {
            p0?.isMyLocationEnabled = true
        }
        googleMap = p0

        // setting the on tap listener on the map
        googleMap?.setOnMapLongClickListener {
            latLonData = it
            googleMap?.addMarker(MarkerOptions().position(it))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.savedPlaces -> {
                // MOVE TO THE NEXT SCREEN
                val intent = Intent(this, SavedPlacesActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
