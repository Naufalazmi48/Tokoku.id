package com.example.tokokuid.core.data.source.remote.network

import com.example.tokokuid.core.data.source.remote.response.CityResponse
import com.example.tokokuid.core.data.source.remote.response.CostResponse
import retrofit2.http.*

interface ApiService {

    @GET("city")
    suspend fun getListCity(
        @Query("key") token:String
    ):CityResponse

    @FormUrlEncoded
    @POST("cost")
    suspend fun getCost(
        @Query("key") token:String,
        @Field("origin") originId:String,
        @Field("destination") destinationId:String,
        @Field("weight") weightItem: Int,
        @Field("courier") courier:String
    ):CostResponse
}