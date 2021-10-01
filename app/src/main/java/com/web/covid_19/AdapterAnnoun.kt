package com.web.covid_19

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.web.covid_19.Home.DataItem
import com.web.covid_19.databinding.ListAnnouncementBinding

data class AdapterAnnoun(
    val listAnnoun : ArrayList<DataItemannoun>,
    val setOnclik : (DataItemannoun) -> Unit
    ): RecyclerView.Adapter<AdapterAnnoun.ViewHolder>() {
    inner  class ViewHolder (val binding: ListAnnouncementBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBinItem (responseAmb: DataItemannoun){
            binding.postBy.text = responseAmb.nama
            binding.tanngal.text = responseAmb.tanngal
            binding.announcement.text= responseAmb.komentar
            binding.listAnnoun.setOnClickListener{
                setOnclik(responseAmb)
            }
        }


    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterAnnoun.ViewHolder {
        val binding = ListAnnouncementBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterAnnoun.ViewHolder, position: Int) {
        val data = listAnnoun[position]
        holder.onBinItem(data)
    }

    override fun getItemCount(): Int {
        return listAnnoun.size
    }

    fun setData(list: List<DataItemannoun>) {
        listAnnoun.clear()
        listAnnoun.addAll(list)
        notifyDataSetChanged()

    }
}

