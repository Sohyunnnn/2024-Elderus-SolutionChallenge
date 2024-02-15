package com.example.elderus

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PlacesApiService {
    @Headers("Content-Type: application/json", "X-Goog-Api-Key: ${BuildConfig.MAPS_API_KEY}", "X-Goog-FieldMask: places.displayName,places.location")
    @POST("v1/places:searchNearby")
    fun searchNearby(@Body body: SearchRequest): Call<SearchResponse>
}