package com.web.covid_19

import com.google.gson.annotations.SerializedName

data class ResponsedetailAnnoun(

	@field:SerializedName("data")
	val data: DataDetailAnoun? = null,

	@field:SerializedName("stastus_code")
	val stastusCode: Int? = null,

	@field:SerializedName("massage")
	val massage: String? = null
)

data class DataDetailAnoun(

	@field:SerializedName("post_by")
	val postBy: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("announcement")
	val announcement: String? = null
)
