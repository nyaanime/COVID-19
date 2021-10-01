package com.web.covid_19.Home

import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageListener
import com.web.Constant
import com.web.covid_19.ApiService
import com.web.covid_19.Detail.DetailIBerita
import com.web.covid_19.Detail.DetailInformasiRs
import com.web.covid_19.RecycleView.Berita
import com.web.covid_19.RecycleView.ListRs
import com.web.covid_19.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var listBerita: AdapteListBerita
    lateinit var infoRs: AdapterInfoRumahSakit
    val image = arrayOf(
        "https://cdn.pixabay.com/photo/2020/03/18/06/14/corona-4942823_960_720.png",
        "https://www.icm-mhi.org/sites/default/files/images/COVID19/covid19.png",
        "https://www.hhsi.us/wp-content/uploads/2020/03/Corona-2.png",
        "https://iik.ac.id/images/assets/covid/doctor_fight.png"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val imagelist = ImageListener { position, imageView ->
            Picasso.get().load(image[position]).into(imageView)
        }
        binding.carocell.pageCount = image.size
        binding.carocell.setImageListener(imagelist)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val s = context?.getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)?.getString(
            Constant.username,"")

        Toast.makeText(context,"$s",Toast.LENGTH_SHORT).show()

        rvInfoRs()
        getInfoRs()

        setupReyclerView()
        getDataBerita()


    }

    private fun rvInfoRs() {
        infoRs = AdapterInfoRumahSakit(arrayListOf()) { data ->
            val intent = Intent(activity, DetailInformasiRs::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
        }
        binding.rvInfo.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = infoRs
        }
        binding.selengkapnya2.setOnClickListener {
            startActivity(Intent(activity, ListRs::class.java))
        }
    }


    //    panggil adapter unttuk bagian info rumah sakit
    private fun getInfoRs() {
        ApiService.getApiEndPoint().getListInfoRs()
            .enqueue(object : Callback<ResponseRs> {
                override fun onResponse(call: Call<ResponseRs>, response: Response<ResponseRs>) {
                    if (response.isSuccessful) {
                        setTodataRsAdapter(response.body()!!)
                        Log.d("setTodataRsAdapter", response.body()!!.toString())
                    } else {
                        Log.e("Home2Fragment", "NULL RESPONSE ON getInfoRs")
                    }

                }

                override fun onFailure(call: Call<ResponseRs>, t: Throwable) {
                    Toast.makeText(activity, "Get data faliure", Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

    private fun setTodataRsAdapter(data: ResponseRs) {
        val dataItem = data.dataRs

        Log.d("setTodataRsAdapter", data.dataRs.toString())
        if (dataItem != null) {
            infoRs.setData(dataItem as List<DataRsItem>)
        }
    }

    //    function list Berita
    private fun setupReyclerView() {
        listBerita = AdapteListBerita(arrayListOf()) { dataItem ->
            val intent = Intent(activity, DetailIBerita::class.java)
            intent.putExtra("data2", dataItem)
            startActivity(intent)
        }
        binding.rvListBerita.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = listBerita
        }
        binding.selengkapnya1.setOnClickListener {
            startActivity(Intent(activity, Berita::class.java))
        }
    }


    private fun getDataBerita() {
        ApiService.getApiEndPoint().getListBerita()
            .enqueue(object : Callback<ResponseBerita> {
                override fun onResponse(
                    call: Call<ResponseBerita>,
                    response: Response<ResponseBerita>
                ) {
                    if (response.isSuccessful) {
                        setDataToAdapter(response.body()!!)
//                        Log.d("home_fragemnt_berita", response.body()!!.toString())
                    }
                }

                override fun onFailure(call: Call<ResponseBerita>, t: Throwable) {
                    Toast.makeText(activity, "getData Failure", Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun setDataToAdapter(resData: ResponseBerita) {
        val dataItem = resData.data
        if (dataItem != null) {
            listBerita.setData(dataItem as List<DataItem>)
        }
    }
//    penutup function list berita
}
