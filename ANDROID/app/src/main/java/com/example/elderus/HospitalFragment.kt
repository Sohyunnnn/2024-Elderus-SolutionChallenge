package com.example.elderus


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HospitalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hospital, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                        latitude = 37.7937,
                        longitude = -122.3965
                    ),
                    radius = 500.0
                )
            )
        )

        val call = service.searchNearby(request)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val places = response.body()?.places ?: emptyList()
                    places.forEach { place ->
                        Log.d("HospitalFragment", "Found place: ${place.displayName.text}")
                    }
                } else {
                    Log.e("HospitalFragment", "API call failed with response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("HospitalFragment", "API call failed with error: ${t.message}")
            }
        })

    }}