package com.example.tokokuid.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CityResponse(

    @field:SerializedName("rajaongkir")
    val rajaongkir: RajaongkirCity? = null
)

data class RajaongkirCity(

    @field:SerializedName("results")
    val results: List<ResultsItemCity?>? = null,

    @field:SerializedName("status")
    val status: StatusCity? = null
)

data class ResultsItemCity(

    @field:SerializedName("city_name")
    val cityName: String? = null,

    @field:SerializedName("city_id")
    val cityId: String? = null
)

data class StatusCity(

    @field:SerializedName("code")
    val code: Int? = null
)
