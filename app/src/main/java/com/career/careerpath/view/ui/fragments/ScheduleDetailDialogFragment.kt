package com.career.careerpath.view.ui.fragments

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.career.careerpath.R
import com.career.careerpath.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import java.text.SimpleDateFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ScheduleDetailDialogFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolBarConference.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolBarConference.setTitleTextColor(Color.WHITE)
        toolBarConference.setOnClickListener {
            dismiss()
        }

        val conference = arguments?.getSerializable("conference") as Conference
        toolBarConference.title = conference.title

        tvTituloConferencia.text = conference.title
        val pattern = "dd/mm/yyyy hh:mm a"
        val simpleDF = SimpleDateFormat(pattern)
        val date = simpleDF.format(conference.datetime)
        tvDetailConferenceHour.text = date
        tvNameSpeaker.text = conference.speaker
        tvTag.text = conference.tag
        tvDescripcion.text = conference.description
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
            //FirebaseCrashlytics.getInstance().log("Crash show dialog fragment isAdded : $isAdded previusFragment != null : ${fragment != null} tag: $tag")
            //FirebaseCrashlytics.getInstance().recordException(exception)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScheduleDetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
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