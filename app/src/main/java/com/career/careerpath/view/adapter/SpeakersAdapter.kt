package com.career.careerpath.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.career.careerpath.R
import com.career.careerpath.databinding.HolderScheduleBinding
import com.career.careerpath.databinding.HolderSpeakerBinding
import com.career.careerpath.model.Conference
import com.career.careerpath.model.Speaker
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SpeakersAdapter(val scheduleListener : ScheduleListener) : RecyclerView.Adapter<SpeakersAdapter.ViewHolder>() {

    var listSpeakers = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.holder_speaker, parent, false
        )
    )

    override fun onBindViewHolder(holder: SpeakersAdapter.ViewHolder, position: Int) {
        //holder.binding.imgExpositor = listConference[position].title

        holder.binding.tvNameExpositor.text = listSpeakers[position].name
        holder.binding.tvTrabajoExpositor.text = listSpeakers[position].jobtitle

    }

    fun ubdateData(data : List<Speaker>){
        listSpeakers.clear()
        listSpeakers.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = listSpeakers.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: HolderSpeakerBinding = HolderSpeakerBinding.bind(itemView)
    }

}