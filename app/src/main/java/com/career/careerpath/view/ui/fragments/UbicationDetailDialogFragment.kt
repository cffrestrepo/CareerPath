package com.career.careerpath.view.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.career.careerpath.R
import com.career.careerpath.model.Ubication
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.toolBarConference
import kotlinx.android.synthetic.main.fragment_ubication_detail_dialog.*

class UbicationDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolBarConference.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolBarConference.setTitleTextColor(Color.WHITE)
        toolBarConference.setOnClickListener {
            dismiss()
        }
        toolBarConference.title = ""//speaker.name

        val ubication = Ubication()

        tvNameLugar.text = ubication.name
        ubicationAdress.text = ubication.address
        phone.text = ubication.phone
        web.text = ubication.website

        // imageLocation
        ctnTelefono.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${ubication.phone}")
            }
            startActivity(intent)
        }

        cntWebSite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(ubication.website)
            }
            startActivity(intent)
        }

    }

}