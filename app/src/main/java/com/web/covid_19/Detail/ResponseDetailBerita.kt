package com.web.covid_19.Detail

import com.google.gson.annotations.SerializedName

data class ResponseDetailBerita(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("stastus_code")
	val stastusCode: Int? = null,

	@field:SerializedName("massage")
	val massage: String? = null
)

data class Data(

	@field:SerializedName("post_on")
	val postOn: String? = null,

	@field:SerializedName("images")
	val images: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("category")
	val category: String? = null,


	@field:SerializedName("content")
	val content: String? = null
)
