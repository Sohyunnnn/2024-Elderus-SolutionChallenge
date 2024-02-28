package com.example.elderus.network_utils

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST

interface UserInterface {
    @POST("/oauth2/authorization/google")
    fun googleOauthLogin() : Call<ResponseBody>

    @POST("/api/v1/ward/join")
    fun seniorJoin(@Body userInfo: UserInfo) : Call<ResponseBody>

    @POST("/api/v1/guardian/join")
    fun guardianJoin(@Body userInfo: UserInfo) : Call<ResponseBody>

    @POST("/api/v1/user/login")
    fun userLogin(@Body loginData: LoginData) : Call<LoginResponse>



}