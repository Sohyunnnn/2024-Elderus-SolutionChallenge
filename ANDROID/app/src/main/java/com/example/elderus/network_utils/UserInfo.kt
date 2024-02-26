package com.example.elderus.network_utils

import com.google.gson.annotations.SerializedName
import java.util.Date

data class UserInfo(
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "password") val password: String,
    @SerializedName(value = "familyName") val familyName: String?,
    @SerializedName(value = "givenName") val givenName: String?,
    @SerializedName(value = "phone") val phone: String?,
    @SerializedName(value = "address") val address: String?,
    @SerializedName(value = "birth") val birth: String?

)
