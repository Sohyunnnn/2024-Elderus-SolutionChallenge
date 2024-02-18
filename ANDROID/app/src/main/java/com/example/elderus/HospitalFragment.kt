package com.example.elderus

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HospitalFragment : Fragment(), OnMapReadyCallback {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hospital, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            this.googleMap = googleMap
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                googleMap.isMyLocationEnabled = true
                getCurrentLocation()
            } else {
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1)
            }
            // 마커 클릭 이벤트 핸들러 설정
            googleMap.setOnMarkerClickListener { marker ->
                val hospitalInfo = marker.tag as? HospitalInfo
                if (hospitalInfo != null) {
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("병원 정보")
//                    builder.setMessage("병원 이름: ${hospitalInfo.name}\n위치: ${hospitalInfo.position}\n주소: ${hospitalInfo.address}\n전화번호: ${hospitalInfo.phoneNumber}")
                    builder.setMessage("병원 이름: ${hospitalInfo.name}\n위치: ${hospitalInfo.position}")
                    builder.setPositiveButton("확인") { dialog, _ ->
                        dialog.dismiss()
                    }
                    builder.show()
                }

                true
            }


        }
    }

    private fun getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            // 위치 권한이 없을 때 권한 요청
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1)
        } else {
            // 위치 권한이 있을 때 위치 정보 가져오기
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        Log.d("현재 위치: ", "Current location: latitude = ${it.latitude}, longitude = ${it.longitude}")
                        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude, it.longitude), 15f)) // 15f는 줌 레벨입니다. 이 값을 조정하여 카메라의 줌 레벨을 변경할 수 있습니다.
                        searchNearbyHospitals(it.latitude, it.longitude)
                    } ?: Log.e("현재 위치: ", "Failed to get location.")
                }
        }
    }





    private fun searchNearbyHospitals(latitude: Double, longitude: Double) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://places.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PlacesApiService::class.java)

        val request = SearchRequest(
            includedTypes = listOf("hospital"),
            maxResultCount = 10,
            locationRestriction = LocationRestriction(
                circle = Circle(
                    center = Center(
                        latitude = latitude,
                        longitude = longitude
                    ),
                    radius = 50000.0
                )
            )
        )

        Log.d("@@HospitalFragment", "Sending request to API: $request")

        val call = service.searchNearby(request)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val places = response.body()?.places ?: emptyList()
                    Log.d("@@HospitalFragment", "Number of hospitals found: ${places.size}")

                    places.forEach { place ->
                        place.location?.let { location ->
                            // 위치 정보를 로그로 출력
                            Log.d("@@HospitalFragment", "Found place: ${place.displayName.text}, location: $location")

                            // 병원 정보를 생성
                            val hospitalInfo = HospitalInfo(
                                name = place.displayName.text,
                                position = LatLng(location.latitude, location.longitude),
                                address = "",     // 여기에 실제 주소 정보를 설정해야 합니다.
                                phoneNumber = ""  // 여기에 실제 전화번호 정보를 설정해야 합니다.
                            )

                            // 병원의 위치를 마커로 표시하고, 마커의 tag에 병원 정보를 저장
                            val marker = googleMap?.addMarker(MarkerOptions().position(hospitalInfo.position).title(hospitalInfo.name))
                            marker?.tag = hospitalInfo
                        } ?: Log.e("@@HospitalFragment", "Location is null for place: ${place.displayName.text}")
                    }
                } else {
                    // API 응답 실패를 로그로 출력
                    Log.e("@@HospitalFragment", "API call failed with response code: ${response.code()}, message: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                // API 호출 실패를 로그로 출력
                Log.e("@@HospitalFragment", "API call failed with error: ${t.message}")
            }
        })
    }
}
