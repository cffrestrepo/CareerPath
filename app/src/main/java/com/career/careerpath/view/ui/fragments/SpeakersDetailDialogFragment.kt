package com.career.careerpath.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.career.careerpath.R
import com.career.careerpath.model.Speaker
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.view.*


class SpeakersDetailDialogFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var speaker: Speaker = Speaker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            speaker = Gson().fromJson(it.getString("speaker") ?: "{}", Speaker::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolBarConference.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolBarConference.setTitleTextColor(Color.WHITE)
        toolBarConference.setOnClickListener {
            dismiss()
        }
        toolBarConference.title = speaker.name
/*
        Glide.with(view.context).load(speaker.image)
            .apply(RequestOptions.circleCropTransform()).into(view.imgSpeaker)
 */
        view.tvNameSpeaker.text = speaker.name
        view.tvTituloTrabajo.text = speaker.jobtitle
        view.tvTrabajo.text = speaker.workplace
        view.tvDescripcion.text = speaker.biography
    }


    override fun show(manager: FragmentManager, tag: String?) {
        val fragment = manager.findFragmentByTag(tag)
        val ft = manager.beginTransaction()
        if (fragment != null) {
            ft.remove(fragment)
            ft.commitAllowingStateLoss()
        }
        try {
            super.show(manager, tag)
        } catch (exception: Exception) {

        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }
}