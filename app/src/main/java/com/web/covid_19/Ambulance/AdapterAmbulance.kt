package com.web.covid_19.Ambulance

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.web.covid_19.databinding.FragmentAmbulanceBinding
import com.web.covid_19.databinding.LlistHospitalBinding

data class AdapterAmbulance(
    val listAmb : ArrayList<DataAmbItem>,

): RecyclerView.Adapter<AdapterAmbulance.ViewHolder>() {
    inner  class ViewHolder (val binding: LlistHospitalBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBinItem (responseAmb: DataAmbItem){
            binding.titleAmb.text = responseAmb.nama
            binding.phone.text = responseAmb.phone
            binding.alamat.text= responseAmb.alamat

        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterAmbulance.ViewHolder {
        val binding =LlistHospitalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterAmbulance.ViewHolder, position: Int) {
        val dataAmb = listAmb[position]
        holder.onBinItem(dataAmb)
    }

    override fun getItemCount(): Int {
        return listAmb.size
    }

    fun setData(list: List<DataAmbItem>) {
        listAmb.clear()
        listAmb.addAll(list)
        notifyDataSetChanged()

    }
}

