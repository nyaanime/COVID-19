package com.web.covid_19.Oxygen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.web.covid_19.Ambulance.DataAmbItem
import com.web.covid_19.databinding.ListOxygenBinding

data class AdapterOxygen(
    val listOxy : ArrayList<DataOxyItem>
): RecyclerView.Adapter<AdapterOxygen.ViewHolder>() {
    inner  class ViewHolder (val binding: ListOxygenBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBinItem (responseOxy: DataOxyItem){
            binding.titleOxygen.text= responseOxy.nama
            binding.phone.text= responseOxy.phone
            binding.almtOxygen.text= responseOxy.alamat
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =ListOxygenBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataOxy = listOxy[position]
        holder.onBinItem(dataOxy)
    }

    override fun getItemCount(): Int {
        return listOxy.size
    }
    fun setData (dataOxyItem: List<DataOxyItem>){
        listOxy.clear()
        listOxy.addAll(dataOxyItem)
        notifyDataSetChanged()
    }

}
