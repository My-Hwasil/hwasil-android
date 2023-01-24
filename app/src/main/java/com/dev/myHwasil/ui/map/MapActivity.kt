package com.dev.myHwasil.ui.map

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.dev.myHwasil.R
import com.dev.myHwasil.data.api.models.TestData
import com.dev.myHwasil.databinding.ActivityMapBinding
import net.daum.mf.map.api.*
import net.daum.mf.map.api.MapPOIItem


class MapActivity : ComponentActivity() {
    private lateinit var binding: ActivityMapBinding
    private val eventListener = MarkerEventListener(this)  // 마커 클릭 이벤트 리스너


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        val view = binding.root

        val mapView = MapView(this)
        binding.mapView.addView(mapView)
        setContentView(view)


        // 마커 클릭 이벤트 리스너 등록
        mapView.setPOIItemEventListener(eventListener)

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


        val markerArr = ArrayList<MapPOIItem>()
        for (data in dataArr) {
            val marker = MapPOIItem()
            marker.mapPoint = MapPoint.mapPointWithGeoCoord(data.latitude, data.longitude)
            marker.itemName = data.name
            marker.markerType = MapPOIItem.MarkerType.CustomImage
            marker.customImageResourceId = R.drawable.toilet_marker
            marker.isCustomImageAutoscale = false
            markerArr.add(marker)
        }

        mapView.addPOIItems(markerArr.toArray(arrayOfNulls(markerArr.size)))

    }


    // 마커 클릭 이벤트 리스너
    class MarkerEventListener(val context: Context) : MapView.POIItemEventListener {

        override fun onPOIItemSelected(mapView: MapView?, poiItem: MapPOIItem?) {

            // 마커 클릭 시
            Toast.makeText(context, "${poiItem?.itemName}", Toast.LENGTH_SHORT).show()

        }

        override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?) {
            // 말풍선 클릭 시 (Deprecated). 밑 함수에 작성
        }

        override fun onCalloutBalloonOfPOIItemTouched(
            mapView: MapView?,
            poiItem: MapPOIItem?,
            buttonType: MapPOIItem.CalloutBalloonButtonType?
        ) {
            // 말풍선 클릭 시
        }

        override fun onDraggablePOIItemMoved(
            mapView: MapView?,
            poiItem: MapPOIItem?,
            mapPoint: MapPoint?
        ) {
            // 마커의 속성 중 isDraggable = true 일 때 마커를 이동시켰을 경우
        }
    }
}