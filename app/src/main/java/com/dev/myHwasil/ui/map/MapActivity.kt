package com.dev.myHwasil.ui.map

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dev.myHwasil.R
import com.dev.myHwasil.data.api.models.TestData
import com.dev.myHwasil.databinding.ActivityMapBinding
import com.dev.myHwasil.ui.dialog.ModalBottomSheet
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import net.daum.mf.map.api.*
import net.daum.mf.map.api.MapPOIItem


class MapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapBinding
    private val eventListener =
        MarkerEventListener(this, onBottomSheetOpen = ::handleBottomSheetOpen)  // 마커 클릭 이벤트 리스너
    lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>;

    fun handleBottomSheetOpen(item: MapPOIItem?) {

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        binding.toiletName.setText(item?.itemName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val mapView = MapView(this)
        binding.mapView.addView(mapView)

        // bottom sheet
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN


        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // 상태가 변함에 따라서 할일들을 적어줍니다.
//                if (newState == STATE_EXPANDED) {
//                    // TODO; 내용을 보여주기 위해 fragment 붙이기...
//                }
            }

            override fun onSlide(bottomSheetView: View, slideOffset: Float) {
                // slideOffset 접힘 -> 펼쳐짐: 0.0 ~ 1.0
                if (slideOffset >= 0) {
                    // 화살표는 완전히 펼치면 180도 돌아가게
                    binding.guideline1.rotation = (1 - slideOffset) * 180F
                }
            }
        })


        // 마커 클릭 이벤트 리스너 등록
        mapView.setPOIItemEventListener(eventListener)

        // 줌 인
        mapView.zoomIn(true);
        // 줌 아웃
        mapView.zoomOut(true);
        val mapPoint = MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633)
        mapView.setMapCenterPointAndZoomLevel(mapPoint, 2, true);

        // 마커 생성
        val marker = MapPOIItem()
        marker.itemName = ""
        marker.mapPoint = mapPoint
        marker.markerType = MapPOIItem.MarkerType.CustomImage
        marker.customImageResourceId = R.drawable.marker // 마커 이미지.
        marker.isCustomImageAutoscale = false
        marker.isShowCalloutBalloonOnTouch = false
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
            marker.isShowCalloutBalloonOnTouch = false
            markerArr.add(marker)
        }

        mapView.addPOIItems(markerArr.toArray(arrayOfNulls(markerArr.size)))

    }

}