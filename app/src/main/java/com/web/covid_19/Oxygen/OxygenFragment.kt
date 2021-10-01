package com.web.covid_19.Oxygen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.web.covid_19.ApiService
import com.web.covid_19.Detail.DetailInformasiRs
import com.web.covid_19.Home.AdapterInfoRumahSakit
import com.web.covid_19.Home.DataRsItem
import com.web.covid_19.databinding.FragmentOxygenBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OxygenFragment : Fragment() {
    lateinit var binding:FragmentOxygenBinding
    lateinit var oxyOpsi: AdapterOxygen
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOxygenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RecyclerViewOxy()
        getListOxy()
    }
    private fun RecyclerViewOxy(){
        oxyOpsi = AdapterOxygen(arrayListOf())
        binding.recyclerViewOxygen.apply {
            layoutManager= LinearLayoutManager(activity)
            this.adapter = oxyOpsi
        }
    }
    //    panggil data ADapter
    private fun getListOxy(){
        binding.progresbar.visibility= View.VISIBLE
        ApiService.getApiEndPoint().getListOxy()
            .enqueue(object : Callback<ResponseOxy> {
                override fun onResponse(call: Call<ResponseOxy>, response: Response<ResponseOxy>) {
                    if (response.isSuccessful) {
                        binding.progresbar.visibility = View.VISIBLE
                        setDataToAdapter(response.body()!!)

                    }
                }

                override fun onFailure(call: Call<ResponseOxy>, t: Throwable) {
                    binding.progresbar.visibility = View.GONE
                    Toast.makeText(activity, "getData Failure", Toast.LENGTH_SHORT).show()
                }
            })

    }
    private fun setDataToAdapter(resdata: ResponseOxy) {

        val dataItem = resdata.dataOxy

//        Log.d("setTodataRsAdapter",data.dataRs.toString())
        if (dataItem != null) {
            oxyOpsi.setData(dataItem as List<DataOxyItem>)
        }
    }

}