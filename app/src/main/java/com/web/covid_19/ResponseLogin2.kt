package com.web.covid_19

import com.google.gson.annotations.SerializedName


data class ResponseLogin2(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Data(

	@field:SerializedName("password")
val password: String? = null,

@field:SerializedName("nama_lengkap")
val namaLengkap: String? = null,

@field:SerializedName("email")
val email: String? = null,

@field:SerializedName("username")
val username: String? = null

)
