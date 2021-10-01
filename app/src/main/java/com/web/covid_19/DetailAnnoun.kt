package com.web.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.web.covid_19.Detail.ResponseDetailBerita
import com.web.covid_19.Home.DataItem
import com.web.covid_19.databinding.ActivityDetailAnnounBinding
import com.web.covid_19.databinding.ActivityDetailIberitaBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailAnnoun : AppCompatActivity() {
    lateinit var binding: ActivityDetailAnnounBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAnnounBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intentBerita = intent.getParcelableExtra<DataItemannoun>("detailannoun")!!
//        Log.d("detailannoun", "ID == ${intentBerita.id!!}")
        getDetailBerita(intentBerita.id!!)
    }

    private fun getDetailBerita(id: String) {
        ApiService.getApiEndPoint().getDetail(id)
            .enqueue(object : Callback<ResponsedetailAnnoun> {
                override fun onResponse(
                    call: Call<ResponsedetailAnnoun>,
                    response: Response<ResponsedetailAnnoun>
                ) {
                    if (response.isSuccessful) {
                        val dataResponse = response.body()!!.data!!
                        binding.judulAnnon.text = dataResponse.judul
                        binding.tanggal.text = dataResponse.tanggal
                        binding.isi.text = dataResponse.announcement

                    }
                }

                override fun onFailure(call: Call<ResponsedetailAnnoun>, t: Throwable) {
                    Toast.makeText(applicationContext, "get detail berita error", Toast.LENGTH_LONG)
                        .show()
                }


            })
    }

}