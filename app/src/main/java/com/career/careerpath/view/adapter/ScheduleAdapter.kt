package com.career.careerpath.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.career.careerpath.R
import com.career.careerpath.databinding.HolderScheduleBinding
import com.career.careerpath.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val scheduleListener : ScheduleListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    var listConference = ArrayList<Conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.holder_schedule, parent, false
        )
    )

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        holder.binding.tvItemConferenceName.text = listConference[position].title
        holder.binding.tvItemConferenceSpeakers.text = listConference[position].speaker
        holder.binding.tvItemTag.text = listConference[position].tag

        var simpleDateFormat = SimpleDateFormat("HH:mm")
        var simpleDateFormatAMPM = SimpleDateFormat("a")

        val hourFormat = simpleDateFormat.format(listConference[position].datetime)
        val hourFormatAmPm = simpleDateFormatAMPM.format(listConference[position].datetime)

        holder.binding.tvItemHora.text = hourFormat
        holder.binding.tvItemHoraAmPm.text = hourFormatAmPm


    }

    fun ubdateData(data : List<Conference>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = listConference.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: HolderScheduleBinding = HolderScheduleBinding.bind(itemView)
    }

}