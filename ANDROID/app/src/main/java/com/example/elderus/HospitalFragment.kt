package com.example.elderus

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        mapFragment?.getMapAsync(this)

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1)
        } else {
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

        val call = service.searchNearby(request)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val places = response.body()?.places ?: emptyList()
                    Log.d("@@HospitalFragment", "Number of hospitals found: ${places.size}")
                    places.forEach { place ->
                        Log.d("@@HospitalFragment", "Found place: ${place.displayName.text}")
                    }
                } else {
                    Log.e("@@HospitalFragment", "API call failed with response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("HospitalFragment", "API call failed with error: ${t.message}")
            }
        })
    }
}
