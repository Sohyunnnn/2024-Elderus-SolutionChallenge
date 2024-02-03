package com.example.elderus

data class PlacesResponse(
    val places: List<Place>
)

data class Place(
    val displayName: DisplayName
)

data class DisplayName(
    val text: String,
    val languageCode: String
)
