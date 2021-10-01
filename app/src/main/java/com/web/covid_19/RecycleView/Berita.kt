package com.web.covid_19.RecycleView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.web.covid_19.ApiService
import com.web.covid_19.Detail.DetailIBerita
import com.web.covid_19.Home.AdapteListBerita
import com.web.covid_19.Home.DataItem
import com.web.covid_19.Home.ResponseBerita
import com.web.covid_19.databinding.ActivityBeritaBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Berita : AppCompatActivity() {
    lateinit var binding : ActivityBeritaBinding
    lateinit var beritaAdapter: AdapteListBerita

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupReyclerView()
        getDataBerita()

    }
    private fun setupReyclerView(){
        beritaAdapter = AdapteListBerita(arrayListOf()){
                dataItem ->
            val intent = Intent(this,DetailIBerita::class.java)
            intent.putExtra("data2",dataItem)
            startActivity(intent)
        }
        binding.recyclerViewBerita.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = beritaAdapter
        }
    }
    private fun getDataBerita(){
        binding.progresbar.visibility= View.VISIBLE
        ApiService.getApiEndPoint().getListBerita()
            .enqueue(object : Callback<ResponseBerita> {
                override fun onResponse(
                    call: Call<ResponseBerita>,
                    response: Response<ResponseBerita>
                ) {
                    binding.progresbar.visibility = View.GONE
                    if (response.isSuccessful){
                        setDataToAdapter(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<ResponseBerita>, t: Throwable) {
                    binding.progresbar.visibility = View.GONE
                    Toast.makeText(this@Berita, "getData Failure", Toast.LENGTH_SHORT).show()
                }

            })
    }
    private fun setDataToAdapter(resData: ResponseBerita){
        val dataItem = resData.data
        if (dataItem != null){
            beritaAdapter.setData(dataItem as List<DataItem>)
        }

    }
}