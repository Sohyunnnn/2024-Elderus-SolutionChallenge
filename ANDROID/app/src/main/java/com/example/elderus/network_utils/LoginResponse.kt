package com.example.elderus.network_utils

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName(value = "role") val role: String,
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "givenName") val givenName: String,
    @SerializedName(value = "familyName") val familyName: String
)
