package com.dev.myHwasil.ui.map

import android.content.Context
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

// 마커 클릭 이벤트 리스너
class MarkerEventListener(val context: Context, val onBottomSheetOpen: (poiItem : MapPOIItem?) -> Unit) :
    MapView.POIItemEventListener {

    // 마커 클릭 시
    override fun onPOIItemSelected(mapView: MapView?, poiItem: MapPOIItem?) {

        onBottomSheetOpen(poiItem);
    }
    // 말풍선 클릭 시 (Deprecated). 밑 함수에 작성
    override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?) {

    }

    // 말풍선 클릭 시
    override fun onCalloutBalloonOfPOIItemTouched(
        mapView: MapView?,
        poiItem: MapPOIItem?,
        buttonType: MapPOIItem.CalloutBalloonButtonType?
    ) {

    }
    // 마커의 속성 중 isDraggable = true 일 때 마커를 이동시켰을 경우
    override fun onDraggablePOIItemMoved(
        mapView: MapView?,
        poiItem: MapPOIItem?,
        mapPoint: MapPoint?
    ) {

    }
}