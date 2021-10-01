package com.web.covid_19.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.web.covid_19.ApiService
import com.web.covid_19.R
import com.web.covid_19.databinding.ListBeritaBinding

data class AdapteListBerita(
    val listBeita: ArrayList<DataItem>,
    val setBeritaOnclik : (DataItem) -> Unit
): RecyclerView.Adapter<AdapteListBerita.ViewHolder>(){

    inner class ViewHolder(val ListBeritaBinding: ListBeritaBinding):
        RecyclerView.ViewHolder(ListBeritaBinding.root){
        fun onBindItem (beritaResponse : DataItem){
            ListBeritaBinding.judulBerita1.text = beritaResponse.judul
            ListBeritaBinding.tanggal1.text = beritaResponse.postOn
            Glide.with(ListBeritaBinding.root.context)
                .load(ApiService.IMAGE_URL + beritaResponse.image)
                .placeholder(R.drawable.no_image_found)
                .error(R.drawable.no_image_found)
                .centerCrop()
                .into(ListBeritaBinding.imageBerita)

            ListBeritaBinding.listBerita.setOnClickListener{
                setBeritaOnclik(beritaResponse)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ListBeritaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem =listBeita[position]
        holder.onBindItem(dataItem)
    }

    override fun getItemCount(): Int {
        return listBeita.size;
    }
    fun setData(dataItem: List<DataItem>){
        listBeita.clear()
        listBeita.addAll(dataItem)
        notifyDataSetChanged()
    }

}

