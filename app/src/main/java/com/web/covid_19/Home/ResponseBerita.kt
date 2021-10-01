package com.web.covid_19.Home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseBerita(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("stastus_code")
	val stastusCode: Int? = null,

	@field:SerializedName("massage")
	val massage: String? = null
)
@Parcelize
data class DataItem(

	@field:SerializedName("post_on")
	val postOn: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("category")
	val category: String? = null
):Parcelable
