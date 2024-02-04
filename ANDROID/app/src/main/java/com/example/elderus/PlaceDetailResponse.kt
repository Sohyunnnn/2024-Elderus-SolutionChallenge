package com.example.elderus

data class PlaceDetailResponse(
    val name: String,
    val location: Location
)

data class Location(
    val lat: Double,
    val lng: Double
)

