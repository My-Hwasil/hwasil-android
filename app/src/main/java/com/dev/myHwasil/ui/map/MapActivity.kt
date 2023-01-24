package com.dev.myHwasil.ui.map


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.dev.myHwasil.R
import com.dev.myHwasil.data.api.models.TestData
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
        val mapPoint = MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633)
        mapView.setMapCenterPointAndZoomLevel(mapPoint, 4, true);

        // 마커 생성
        val marker = MapPOIItem()
        marker.itemName = ""
        marker.mapPoint = mapPoint
        marker.markerType = MapPOIItem.MarkerType.CustomImage
        marker.customImageResourceId = R.drawable.marker // 마커 이미지.
        marker.isCustomImageAutoscale = false
        marker.setCustomImageAnchor(0.5f, 1.0f);
        mapView.addPOIItem(marker)


        var dataArr = listOf<TestData>(
            TestData("서울방송고등학교 화장실", 37.54717402268097, 127.01035369951688),
            TestData("한남더힐 아파트 화장실", 37.5373753, 127.005578),
            TestData("미얀마 대사관 화장실", 37.54005668980562, 127.00269614863929),
        )

        for (data in dataArr) {
            val marker = MapPOIItem()
            marker.mapPoint = MapPoint.mapPointWithGeoCoord(data.latitude, data.longitude)
            marker.itemName = data.name
            marker.markerType = MapPOIItem.MarkerType.CustomImage
            marker.customImageResourceId = R.drawable.toilet_marker
            marker.isCustomImageAutoscale = false
            mapView.addPOIItem(marker)
        }


    }
}