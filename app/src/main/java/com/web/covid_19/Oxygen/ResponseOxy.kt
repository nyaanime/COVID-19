package com.web.covid_19.Oxygen

import com.google.gson.annotations.SerializedName

data class ResponseOxy(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data_oxy")
	val dataOxy: List<DataOxyItem?>? = null,

	@field:SerializedName("massage")
	val massage: String? = null
)

data class DataOxyItem(

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
