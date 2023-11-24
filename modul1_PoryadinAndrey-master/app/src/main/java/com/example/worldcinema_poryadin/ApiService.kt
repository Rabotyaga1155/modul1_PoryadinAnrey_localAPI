package com.example.worldcinema_poryadin


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("/")
    fun getLogins(): Call<ApiResponse>

    @POST("/")
    fun postLogin(@Body request: Map<String, String>): Call<ApiResponse>
}