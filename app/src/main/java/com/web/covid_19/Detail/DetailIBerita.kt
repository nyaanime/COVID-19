package com.web.covid_19.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.web.covid_19.ApiService
import com.web.covid_19.Home.DataItem
import com.web.covid_19.Home.ResponseBerita
import com.web.covid_19.R
import com.web.covid_19.databinding.ActivityDetailIberitaBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailIBerita : AppCompatActivity() {
    lateinit var binding: ActivityDetailIberitaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailIberitaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intentBerita = intent.getParcelableExtra<DataItem>("data2")!!
        Log.d("DetailBeritaID","ID == ${intentBerita.id!!}")
        getDetailBerita(intentBerita.id!!)
    }
    private fun getDetailBerita(id:String){
        ApiService.getApiEndPoint().getDetailBerita(id)
            .enqueue(object : Callback<ResponseDetailBerita> {
                override fun onResponse(
                    call: Call<ResponseDetailBerita>,
                    response: Response<ResponseDetailBerita>
                ) {
                    if (response.isSuccessful){
                        val dataResponse = response.body()!!.data!!
                        binding.judulBerita.text = dataResponse.judul
                        binding.tanggal.text = dataResponse.postOn
                        Glide.with(this@DetailIBerita)
                            .load(ApiService.IMAGE_URL + dataResponse.images)
                            .centerCrop()
                            .placeholder(R.drawable.no_image_found)
                            .error(R.drawable.no_image_found)
                            .into(binding.imgBerita)
                        binding.deskripsi.text = dataResponse.content

                    }
                }

                override fun onFailure(call: Call<ResponseDetailBerita>, t: Throwable) {
                    Toast.makeText(applicationContext,"get detail berita error", Toast.LENGTH_LONG).show()
                }


            })
    }
}