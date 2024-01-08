package com.example.elderus

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.elderus.databinding.FragmentHospitalBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

class HospitalFragment : Fragment(R.layout.fragment_hospital),
    OnMapReadyCallback {

    private lateinit var mGoogleMap: GoogleMap
    private lateinit var mContext: Context
    private lateinit var binding: FragmentHospitalBinding // 데이터 바인딩 클래스
    private lateinit var mapView: MapView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHospitalBinding.bind(view) // 데이터 바인딩 초기화
        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    //GoogleMap Setting
    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap  // mGoogleMap 초기화

        val seoul = LatLng(37.554891, 126.970814)

        mGoogleMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        mGoogleMap.apply {
            moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 15F))
            // 추가적인 Google Map 설정
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
