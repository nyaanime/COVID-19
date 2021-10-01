package com.web.covid_19.Ambulance

import com.google.gson.annotations.SerializedName

data class ResponseAmb(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data_amb")
	val dataAmb: List<DataAmbItem?>? = null,

	@field:SerializedName("massage")
	val massage: String? = null
)

data class DataAmbItem(

	@field:SerializedName("post_on")
	val postOn: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)
