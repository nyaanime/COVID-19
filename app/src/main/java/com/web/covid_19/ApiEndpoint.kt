package com.web.covid_19

import com.web.covid_19.Ambulance.ResponseAmb
import com.web.covid_19.Detail.ResponseDetailBerita
import com.web.covid_19.Home.ResponseBerita
import com.web.covid_19.Home.ResponseRs
import com.web.covid_19.Oxygen.ResponseOxy


import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {
    @GET("api_list_amb.php")
    fun getListAmb(): Call<ResponseAmb>
    @GET("api_list_oxy.php")
    fun getListOxy(): Call<ResponseOxy>

    @GET("api_list_rs.php")
    fun getListInfoRs(): Call<ResponseRs>

    @GET("api_list_berita.php")
    fun getListBerita(): Call<ResponseBerita>
    @GET("api_detail_berita.php")
    fun getDetailBerita(@Query("id") id: String):Call<ResponseDetailBerita>
    @FormUrlEncoded
    @POST("../regist.php")
    fun postRegis(
        @Field("username") username : String?,
        @Field("password") password : String?,
        @Field("nama lengkap") nama_lengkap : String?,
        @Field("email") email : String?,
    ): Call<ResponseRegis>
    @POST("../login.php")
    @FormUrlEncoded
    fun postLogin(
        @Field("username") username : String?,
        @Field("password") password : String?,
    ): Call<ResponseLogin2>
    @POST("api_feedback.php")
    @FormUrlEncoded
    fun postFeeedback(
        @Field("username") username : String?,
        @Field("komentar") komentar : String?,
    ): Call<ResponseFeedback>
    @GET("announcement.php")
    fun getListAnnoun(): Call<ResponseAnnoun>
    @GET("../announcement_detail.php")
    fun getDetail(@Query("id") id: String):Call<ResponsedetailAnnoun>

}
