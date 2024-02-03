package com.example.elderus


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import android.location.Location
import android.util.Log
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.gson.GsonBuilder
import java.io.IOException
import okhttp3.*
import okhttp3.Callback
import okhttp3.Call
import okhttp3.OkHttpClient
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONArray
import org.json.JSONObject


class HospitalFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var placesClient: PlacesClient
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val REQUEST_LOCATION_PERMISSION = 1
    private lateinit var apiKey: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_hospital, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val applicationInfo = requireContext().packageManager.getApplicationInfo(requireContext().packageName, PackageManager.GET_META_DATA)
        apiKey = applicationInfo.metaData.getString("com.google.android.geo.API_KEY")!!

        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), apiKey)
        }
        placesClient = Places.createClient(requireContext())
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        // 위치 권한 확인
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 권한 요청
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        }

        return root
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 부여된 경우 지도와 Places API 초기화
                val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this)
            } else {
                // 권한이 거부된 경우 알림 표시
                Toast.makeText(requireContext(), "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getCurrentLocation(100, null).addOnSuccessListener { location: Location? ->
                location?.let {
                    Log.d("Location", "Latitude: ${location.latitude}, Longitude: ${location.longitude}")

                    val currentLocation = LatLng(location.latitude, location.longitude)
                    val radius = 100000

                    val json = JSONObject().apply {
                        put("includedTypes", JSONArray(arrayListOf("hospital")))
                        put("maxResultCount", 10)
                        put("locationRestriction", JSONObject().apply {
                            put("circle", JSONObject().apply {
                                put("center", JSONObject().apply {
                                    put("latitude", currentLocation.latitude)
                                    put("longitude", currentLocation.longitude)
                                })
                                put("radius", radius.toDouble())
                            })
                        })
                    }
                    val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json.toString())

                    val request = Request.Builder()
                        .url("https://places.googleapis.com/v1/places:searchNearby")
                        .post(requestBody)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("X-Goog-Api-Key", apiKey)
                        .addHeader("X-Goog-FieldMask", "places.displayName")
                        .build()

                    val client = OkHttpClient()
                    client.newCall(request).enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            Log.e("Network Request", "Failed to make request", e)
                            activity?.runOnUiThread {
                                Toast.makeText(requireContext(), "네트워크 요청에 실패했습니다.", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onResponse(call: Call, response: Response) {
                            val body = response.body?.string()
                            Log.d("@@Places API Response", body ?: "Empty response")

                            val gson = GsonBuilder().create()
                            val placesResponse = gson.fromJson<PlacesResponse>(body, object : TypeToken<PlacesResponse>() {}.type)

                            placesResponse.places.forEach { place ->
                                val placeId = place.displayName.text
                                val detailUrl = "https://places.googleapis.com/v1/places/$placeId?languageCode=en&key=apiKey"
                                val detailRequest = Request.Builder()
                                    .url(detailUrl)
                                    .build()
                                client.newCall(detailRequest).enqueue(object : Callback {
                                    override fun onFailure(call: Call, e: IOException) {
                                        Log.e("Detail Request", "Failed to make request", e)
                                    }

                                    override fun onResponse(call: Call, response: Response) {
                                        val body = response.body?.string()
                                        val detailResponse = gson.fromJson<PlaceDetailResponse>(body, PlaceDetailResponse::class.java)
                                        activity?.runOnUiThread {
                                            val latLng = LatLng(detailResponse.location.lat, detailResponse.location.lng)
                                            val markerOptions = MarkerOptions()
                                                .position(latLng)
                                                .title(place.displayName.text)
                                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                                            mMap.addMarker(markerOptions)
                                        }
                                    }

                                })
                            }
                        }


                    })

                    val zoomLevel = 15.0f
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, zoomLevel))

                    val markerOptions = MarkerOptions()
                        .position(currentLocation)
                        .title("현재 위치")
                    mMap.addMarker(markerOptions)

                } ?: run {
                    Log.e("Location", "Failed to get current location.")
                    Toast.makeText(requireContext(), "현재 위치를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { e ->
                Log.e("Location", "Failed to get current location: ${e.message}")
                Toast.makeText(requireContext(), "현재 위치를 가져오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        }
    }





}