package com.example.worldcinema_poryadin


import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("userprogress")
    fun getLogins(): Call<UserResponse>

    @GET("userprogress")
    fun getRank():Call<PayLoadResponse>

    @GET("social/friends/507f191e810c19729de860ea")
    fun getFriends():Call<FriendsResponse>

    @GET("social/events/507f191e810c19729de860ea")
    fun getEvents(): Call<EventResponse>

    @POST("social/post/{userId}")
    fun postMessage(
        @Path("userId") userId: String,
        @Body request: PostMessageRequest
    ): Call<Response<Void>>


}