package com.web.covid_19

import com.google.gson.annotations.SerializedName

data class ResponseFeedback(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)
