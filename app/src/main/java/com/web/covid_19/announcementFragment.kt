package com.web.covid_19

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.web.covid_19.Ambulance.AdapterAmbulance
import com.web.covid_19.Ambulance.DataAmbItem
import com.web.covid_19.Ambulance.ResponseAmb
import com.web.covid_19.Detail.DetailIBerita
import com.web.covid_19.Home.AdapteListBerita
import com.web.covid_19.databinding.FragmentAmbulanceBinding
import com.web.covid_19.databinding.FragmentAnnouncementBinding
import retrofit2.Call
import retrofit2.Response


class announcementFragment : Fragment() {
    lateinit var binding: FragmentAnnouncementBinding
    lateinit var Opsi: AdapterAnnoun
//    lateinit var listBerita: AdapteListBerita
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAnnouncementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RecyclerView()
        getListAnnoun()

    }

    private fun RecyclerView() {
//        Opsi = AdapterAnnoun(arrayListOf())
//        val intent = Intent(activity,DetailAnnoun::class.java)
////      inisialisasi nilai pada variabl opsi
//        intent.putExtra("data2", DataDetailAnoun)
//        startActivity(intent)

        Opsi = AdapterAnnoun(arrayListOf()) { dataItem ->
            val intent = Intent(activity, DetailAnnoun::class.java)
            intent.putExtra("detailannoun", dataItem)
            startActivity(intent)
        }

        binding.rcAnnoun.apply {
            layoutManager= LinearLayoutManager(activity)
            adapter = Opsi
        }
    }

//    private fun getListAnnoun(){
//        ApiService.getApiEndPoint().getListAnnoun()
//            .enqueue(object : retrofit2.Callback<ResponseAnnoun>{
//                override fun onResponse(
//                    call: Call<ResponseAnnoun>,
//                    response: Response<ResponseAnnoun>
//                ) {
//                   if(response.isSuccessful){
//                       setDataToAdapter(response.body()!!)
//                   }else{
//                       Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
//                   }
//                }
//
//                override fun onFailure(call: Call<ResponseAnnoun>, t: Throwable) {
//                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
//                }
//
//            })
//    }

    //    panggil data ADapter
    private fun getListAnnoun() {
       binding.progresbar.visibility = View.VISIBLE
        ApiService.getApiEndPoint().getListAnnoun()
            .enqueue(object : retrofit2.Callback<ResponseAnnoun> {
                override fun onResponse(
                    call: Call<ResponseAnnoun>,
                    response: Response<ResponseAnnoun>
                ) {
                    binding.progresbar.visibility = View.GONE
                    if (response.isSuccessful) {
                        setDataToAdapter(response.body()!!)
                        val e= response.body()
                        Log.i("dataannounce", "$e")
                    }
                }

                override fun onFailure(call: Call<ResponseAnnoun>, t: Throwable) {
//                    binding.progresbar.visibility = View.GONE
                    Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                }
            })

    }

    private fun setDataToAdapter(resdata: ResponseAnnoun) {
        val dataAnnoun =resdata.data
        Opsi.setData(dataAnnoun as List<DataItemannoun>)

    }


}