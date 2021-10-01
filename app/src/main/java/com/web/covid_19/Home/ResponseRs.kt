package com.web.covid_19.Home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseRs(

	@field:SerializedName("data_rs")
	val dataRs: List<DataRsItem?>? = null,

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("massage")
	val massage: String? = null
)
@Parcelize
data class DataRsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("latlng")
	val latlng: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
):Parcelable
