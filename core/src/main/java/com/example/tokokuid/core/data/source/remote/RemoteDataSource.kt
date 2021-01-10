package com.example.tokokuid.core.data.source.remote

import android.util.Log
import com.example.tokokuid.core.BuildConfig
import com.example.tokokuid.core.data.source.remote.network.ApiResponse
import com.example.tokokuid.core.data.source.remote.network.ApiService
import com.example.tokokuid.core.data.source.remote.response.CityResponse
import com.example.tokokuid.core.data.source.remote.response.CostResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getListCity(): Flow<ApiResponse<CityResponse>> = flow {
        try {
            val response = apiService.getListCity(BuildConfig.API_KEY)
            if (response.rajaongkir?.status?.code == 200) {
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)


    fun getCost(originId:String,destinationId:String,weightItem:Int,courier:String):Flow<ApiResponse<CostResponse>> = flow{
        try {
            val response = apiService.getCost(BuildConfig.API_KEY,originId,destinationId,weightItem,courier)
            if (response.rajaongkir?.status?.code == 200) {
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Empty)
            }
        }catch (e:Exception){
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }
}