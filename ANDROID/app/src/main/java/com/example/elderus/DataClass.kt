package com.example.elderus

data class SearchRequest(
    val includedTypes: List<String>,
    val maxResultCount: Int,
    val locationRestriction: LocationRestriction
)

data class LocationRestriction(
    val circle: Circle
)

data class Circle(
    val center: Center,
    val radius: Double
)

data class Center(
    val latitude: Double,
    val longitude: Double
)

data class SearchResponse(
    val places: List<Place>
)

data class Place(
    val displayName: DisplayName,
    val location: LatLng
)

data class LatLng(
    val latitude: Double,
    val longitude: Double
)


data class DisplayName(
    val text: String,
    val languageCode: String
)

data class HospitalInfo(
    val name: String,
    val position: LatLng,
    val address: String,
    val phoneNumber: String
)
