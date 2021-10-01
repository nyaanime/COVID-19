package com.web.covid_19.Detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.web.covid_19.ApiService
import com.web.covid_19.Home.DataRsItem
import com.web.covid_19.Home.ResponseRs
import com.web.covid_19.R
import com.web.covid_19.databinding.ActivityDetailInformasiRsBinding


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailInformasiRs : AppCompatActivity() {
    private lateinit var binding:ActivityDetailInformasiRsBinding
    private var maps: GoogleMap?= null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityDetailInformasiRsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataInfoRs: DataRsItem = intent.getParcelableExtra("data")!!
        Log.d("detailinfo","image == ${dataInfoRs.image}")
        Glide.with(binding.root.context)
            .load(ApiService.IMAGE_URL + dataInfoRs.image)
            .placeholder(R.drawable.no_image_found)
            .error(R.drawable.no_image_found)
            .centerCrop()
            .into(binding.imageRs)
        binding.NamaRs.text= dataInfoRs.nama
        binding.detailAlamat.text= dataInfoRs.alamat


        with(binding.mapview){
            onCreate(savedInstanceState)
            getMapAsync(){map->
                this@DetailInformasiRs.maps= map
                MapsInitializer.initialize(context)
                val coordinate =dataInfoRs.latlng?.split(",")!!
                val lat =coordinate[0].toDouble()
                val long = coordinate[1].toDouble()
                addMarkerToMaps(LatLng(lat,long),map)
            }

        }
        this.binding.informasi.setOnClickListener {
            //intent explicit
            try {
                val coordinate =dataInfoRs.latlng?.split(",")!!
                val lat =coordinate[0].toDouble()
                val long = coordinate[1].toDouble()
                val gmmIntentUri =
                    Uri.parse("google.navigation:q=${lat},${long}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            } catch (e: ActivityNotFoundException) {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Maps Tidak Terinstall",
                    Snackbar.LENGTH_LONG
                ).setAction("Buka Playstore") {
                    val marketIntent = Intent(Intent.ACTION_VIEW)
                    marketIntent.data = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
                    startActivity(Intent.createChooser(marketIntent, "Lanjutkan..."))
                }.show()
            }
        }
    }

    private fun addMarkerToMaps(latLng: LatLng, maps: GoogleMap) {
        maps.addMarker(MarkerOptions().position(latLng))
        maps.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
    }

    override fun onResume() {
        this.binding.mapview.onResume()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        this.binding.mapview.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding.mapview.onDestroy()
    }
}