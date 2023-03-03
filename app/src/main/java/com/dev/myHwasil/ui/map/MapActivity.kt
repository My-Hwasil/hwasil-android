package com.dev.myHwasil.ui.map

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dev.myHwasil.R
import com.dev.myHwasil.common.result.Results
import com.dev.myHwasil.databinding.ActivityMapBinding
import com.dev.myHwasil.ui.map.MapActivityViewModel
import com.dev.myHwasil.ui.map.MarkerEventListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import net.daum.mf.map.api.*
import net.daum.mf.map.api.MapPOIItem


@AndroidEntryPoint
class MapActivity : AppCompatActivity() {
    private val vm by viewModels<MapActivityViewModel>()

    private lateinit var binding: ActivityMapBinding
    private val eventListener =
        MarkerEventListener(
            this,
            onBottomSheetOpen = ::handleBottomSheetOpen
        )  // 마커 클릭 이벤트 리스너

    lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapView = initMapView()
        initActionMenuView()
        initBottomSheet()

        // ####### 화장실 정보 불러오기 #######
        vm.fetchToiletInfoByAddress(address = "영등포구")

        // ######## 불러온 화장실 데이터로 마커 찍기 ############
        lifecycleScope.launch {
            vm.toiletsFlow.collect {
                when (it) {
                    is Results.Error -> {
                        Log.d("ddd", "onCreate: error ${it.exception}")
                    }

                    is Results.Loading -> {
                        Log.d("ddd", "onCreate: loading")
                    }

                    is Results.Success -> {
                        Log.d("ddd", "onCreate: success ${it.data}")

                        it.data.map { toilet ->
                            createMarker(
                                toilet.title,
                                toilet.latitude,
                                toilet.longitude
                            )
                        }.let { markers ->
                            mapView.addPOIItems(markers.toTypedArray());
                        }
                    }
                }
            }
        }
    }

    private fun initMapView(): MapView {
        val defaultMapPoint =MapPoint.mapPointWithGeoCoord(
            37.516168,
            126.915405
        )

        return MapView(this)
            .apply {
                setPOIItemEventListener(eventListener)
                zoomIn(true)
                zoomOut(true)
                setMapCenterPointAndZoomLevel(
                    defaultMapPoint,
                    2,
                    true
                )
            }.apply {
                addPOIItem(
                    MapPOIItem().apply {
                        itemName = ""
                        mapPoint = defaultMapPoint
                        markerType = MapPOIItem.MarkerType.CustomImage
                        customImageResourceId = R.drawable.marker // 마커 이미지.
                        isCustomImageAutoscale = false
                        isShowCalloutBalloonOnTouch = false
                        setCustomImageAnchor(
                            0.5f,
                            1.0f
                        )
                    }
                )
            }.also {
                binding.mapView.addView(it)
            }
    }

    private fun initActionMenuView() {
        val actionMenuView = binding.menuView;
        menuInflater.inflate(R.menu.bottom_bar_menu_left, actionMenuView.menu)
    }

    private fun initBottomSheet() {
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
    }

    private fun createMarker(toiletName: String, latitude: Double, longitude: Double): MapPOIItem {
        return MapPOIItem()
            .apply {
                mapPoint = MapPoint.mapPointWithGeoCoord(
                    latitude,
                    longitude
                )
                itemName = toiletName
                markerType = MapPOIItem.MarkerType.CustomImage
                customImageResourceId = R.drawable.toilet_marker
                isCustomImageAutoscale = false
                isShowCalloutBalloonOnTouch = false
            }
    }

    private fun handleBottomSheetOpen(item: MapPOIItem?) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        binding.toiletName.text = item?.itemName
    }

}