package com.example.eventease.ApiService


import androidx.core.app.NotificationCompat.MessagingStyle.Message
import com.example.eventease.Models.AkunModel
import com.example.eventease.Models.EventDetailResponse
import com.example.eventease.Models.HistoryResponse
import com.example.eventease.Models.ItemEventModel
import com.example.eventease.Models.LoginRequest
import com.example.eventease.Models.LoginResponse
import com.example.eventease.Models.RecycleHistoryModel
import com.example.eventease.Models.TicketResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    data class Response(val status: Int, val message: String)

    @POST("/api/user/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("/api/user/register")
    fun register(
        @Body requestBody: Map<String, String>
    ): Call<Void?>?

    @GET("/api/user/profile")
    fun profile(@Header("Authorization") token: String): Call<profileResponse>
    data class profileResponse(val status: Int, val values: List<AkunModel>)


    @PUT("/api/user/profile/edit")
    fun updateUserProfile(
        @Header("Authorization") token: String,
        @Body requestBody: Map<String, String>
    ): Call<Void?>?

    @PUT("/api/user/profile/password")
    fun updatePassword(
        @Header("Authorization") token: String,
        @Body requestBody: Map<String, String>
    ): Call<Void?>?

    @GET("/api/user/events")
    fun fetchEvent(
        @Header("Authorization") token: String,
        @Query("limit") limit: Int? = null
    ): Call<List<ItemEventModel>>

    @GET("/api/user/events/recommended")
    fun fetchRecomended(
        @Header("Authorization") token: String,
        @Query("limit") limit: Int? = null
    ): Call<List<ItemEventModel>>

    @GET("/api/user/event/{id_event}")
    fun getEventDetails(
        @Header("Authorization") token: String,
        @Path("id_event") id_event: Int
    ): Call<EventDetailResponse>


    @POST("/api/user/order")
    fun makeOrder(
        @Header("Authorization") token: String,
        @Body requestBody: Map<String, String>
    ): Call<orderResponse>?

    data class orderResponse(val status: Int, val id_histories: Int, val message: String)

    @PUT("/api/user/order/cancel/{id_history}")
    fun cancelOrder(
        @Header("Authorization") token: String,
        @Path("id_history") id_history: Int
    ): Call<Response>

    @PUT("/api/user/order/confirm/{id_history}")
    fun confPay(
        @Header("Authorization") token: String,
        @Path("id_history") id_history: Int
    ): Call<Response>

    @GET("/api/user/history/{id_history}")
    fun getHistory(
        @Header("Authorization") token: String,
        @Path("id_history") id_history: Int
    ): Call<HistoryResponse>

    @GET("/api/user/histories")
    fun getAllHistory(
        @Header("Authorization") token: String,
        @Query("paid") limit: Int? = null
    ): Call<ArrayList<RecycleHistoryModel>>


}