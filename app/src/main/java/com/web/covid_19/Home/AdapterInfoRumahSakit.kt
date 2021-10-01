package com.web.covid_19.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.web.covid_19.ApiService
import com.web.covid_19.R
import com.web.covid_19.databinding.ListInfoRsBinding


data class AdapterInfoRumahSakit(
    val listInfors: ArrayList<DataRsItem>,
    val clientInfoStatus: (DataRsItem) -> Unit
):RecyclerView.Adapter<AdapterInfoRumahSakit.ViewHolder>() {

    inner class ViewHolder(val bindingRs: ListInfoRsBinding):RecyclerView.ViewHolder(bindingRs.root){
        fun onBinItem(Response: DataRsItem){
            bindingRs.info1.text= Response.nama
            bindingRs.lokasi1.text= Response.alamat
            Glide.with(bindingRs.root.context)
                .load(ApiService.IMAGE_URL + Response.image)
                .placeholder(R.drawable.no_image_found)
                .error(R.drawable.no_image_found)
                .centerCrop()
                .into(bindingRs.imageRs)
            bindingRs.listRs.setOnClickListener(){
                clientInfoStatus(Response)
            }

        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterInfoRumahSakit.ViewHolder {
        val binding = ListInfoRsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterInfoRumahSakit.ViewHolder, position: Int) {
        val data= listInfors[position]
        holder.onBinItem(data)

    }

    override fun getItemCount(): Int {
        return listInfors.size
    }
    fun setData(dataItem: List<DataRsItem>){
        listInfors.clear()
        listInfors.addAll(dataItem)
        notifyDataSetChanged()
    }

}