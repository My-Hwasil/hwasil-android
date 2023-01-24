package com.dev.myHwasil.ui.map

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.dev.myHwasil.databinding.ActivityMapBinding
import net.daum.mf.map.api.MapView




class MapActivity : ComponentActivity() {
    private lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        val view = binding.root


        val mapView = MapView(this)
        binding.mapView.addView(mapView)

        setContentView(view)
    }
}