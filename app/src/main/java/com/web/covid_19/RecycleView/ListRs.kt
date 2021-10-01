package com.web.covid_19.RecycleView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.web.covid_19.ApiService
import com.web.covid_19.Detail.DetailInformasiRs
import com.web.covid_19.Home.AdapterInfoRumahSakit
import com.web.covid_19.Home.DataRsItem
import com.web.covid_19.Home.ResponseRs
import com.web.covid_19.R
import com.web.covid_19.databinding.ActivityListRsBinding
import com.web.covid_19.databinding.ListInfoRsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListRs : AppCompatActivity() {
    lateinit var binding: ActivityListRsBinding
    lateinit var infoRs: AdapterInfoRumahSakit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListRsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvInfoRs()
        getInfoRs()
    }
    private fun rvInfoRs() {
        infoRs = AdapterInfoRumahSakit(arrayListOf()) { data ->
            val intent = Intent(this, DetailInformasiRs::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
        }
        binding.rvInfo.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = infoRs
        }

    }
    private fun getInfoRs(){
        binding.progresbar.visibility= View.VISIBLE
        ApiService.getApiEndPoint().getListInfoRs()
            .enqueue(object : Callback<ResponseRs> {
                override fun onResponse(call: Call<ResponseRs>, response: Response<ResponseRs>) {
                    if(response.isSuccessful){
                        binding.progresbar.visibility= View.GONE
                        setTodataRsAdapter(response.body()!!)
                        Log.d("setTodataRsAdapter", response.body()!!.toString())
                    }else{
                        Log.e("Home2Fragment","NULL RESPONSE ON getInfoRs")
                    }

                }

                override fun onFailure(call: Call<ResponseRs>, t: Throwable) {
                    binding.progresbar.visibility= View.GONE
                    Toast.makeText(this@ListRs,"Get data faliure", Toast.LENGTH_SHORT).show()
                }

            }
            )
    }

    private fun setTodataRsAdapter(data: ResponseRs) {
        val dataItem = data.dataRs

        Log.d("setTodataRsAdapter",data.dataRs.toString())
        if (dataItem != null){
            infoRs.setData(dataItem as List<DataRsItem>)
        }
    }

}