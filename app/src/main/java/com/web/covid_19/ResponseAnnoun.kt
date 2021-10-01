package com.web.covid_19

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseAnnoun(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItemannoun?>? = null,

	@field:SerializedName("massage")
	val massage: String? = null
)
@Parcelize
data class DataItemannoun(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("post")
	val post: String? = null,

	@field:SerializedName("komentar")
	val komentar: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("tanngal")
	val tanngal: String? = null
):Parcelable
