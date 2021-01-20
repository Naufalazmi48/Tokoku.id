package com.example.tokokuid.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CostResponse(

	@field:SerializedName("rajaongkir")
	val rajaongkir: Rajaongkir? = null
)

data class ResultsItem(

	@field:SerializedName("costs")
	val costs: List<CostsItem?>? = null

)

data class CostsItem(

	@field:SerializedName("cost")
	val cost: List<CostItem?>? = null,

	@field:SerializedName("service")
	val service: String? = null,

	@field:SerializedName("description")
	val description: String? = null
)

data class Rajaongkir(

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null,

	@field:SerializedName("status")
	val status: Status? = null
)


data class Status(

	@field:SerializedName("code")
	val code: Int? = null
)


data class CostItem(

	@field:SerializedName("note")
	val note: String? = null,

	@field:SerializedName("etd")
	val etd: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)
