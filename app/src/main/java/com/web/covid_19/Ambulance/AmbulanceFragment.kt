package com.web.covid_19.Ambulance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.web.covid_19.ApiService
import com.web.covid_19.databinding.FragmentAmbulanceBinding
import retrofit2.Call
import retrofit2.Response

class AmbulanceFragment : Fragment() {
    lateinit var binding: FragmentAmbulanceBinding
    lateinit var Opsi: AdapterAmbulance

    //    memanggil halaman Fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAmbulanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    //tempat pemnggilan function
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getListRcAmb()
        RecyclerView()

    }

    private fun RecyclerView() {
        Opsi = AdapterAmbulance(arrayListOf())
//      inisialisasi nilai pada variabl opsi
        binding.rcHospital.apply {
            layoutManager= LinearLayoutManager(activity)
            adapter = Opsi
        }
    }

    //    panggil data ADapter
    private fun getListRcAmb() {
        binding.progresbar.visibility = View.VISIBLE
        ApiService.getApiEndPoint().getListAmb()
            .enqueue(object : retrofit2.Callback<ResponseAmb> {
                override fun onResponse(
                    call: Call<ResponseAmb>,
                    response: Response<ResponseAmb>
                ) {
                    binding.progresbar.visibility = View.GONE
                    if (response.isSuccessful) {
                        setDataToAdapter(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<ResponseAmb>, t: Throwable) {
                    binding.progresbar.visibility = View.GONE
                    Toast.makeText(activity, "getData Failure", Toast.LENGTH_SHORT).show()
                }
            })

    }

    private fun setDataToAdapter(resdata: ResponseAmb) {
        val dataOxy =resdata.dataAmb
        Opsi.setData(dataOxy as List<DataAmbItem>)

    }

}