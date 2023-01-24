package com.dev.myHwasil.ui.map


import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.dev.myHwasil.R

import com.dev.myHwasil.databinding.ActivityMapBinding
import net.daum.mf.map.api.*


class MapActivity : ComponentActivity() {
    private lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        val view = binding.root

        val mapView = MapView(this)
        binding.mapView.addView(mapView)
        setContentView(view)

        // 줌 인
        mapView.zoomIn(true);
        // 줌 아웃
        mapView.zoomOut(true);

        // 중심점 변경
        val mapPoint = MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633)
        mapView.setMapCenterPointAndZoomLevel(mapPoint, 2, true);

        // 마커 생성
        val marker = MapPOIItem()
        marker.itemName = "이곳이 수원 화성입니다"
        marker.mapPoint = mapPoint
        marker.markerType = MapPOIItem.MarkerType.CustomImage
        marker.customImageResourceId = R.drawable.marker // 마커 이미지.
        marker.isCustomImageAutoscale = false
        marker.setCustomImageAnchor(0.5f, 1.0f);

        mapView.addPOIItem(marker)

    }
}