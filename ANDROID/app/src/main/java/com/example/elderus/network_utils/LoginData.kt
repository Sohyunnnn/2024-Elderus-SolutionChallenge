package com.example.elderus.network_utils

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "password") val password: String
)
