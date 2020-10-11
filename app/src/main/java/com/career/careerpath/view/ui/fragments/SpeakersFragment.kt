package com.career.careerpath.view.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.career.careerpath.R
import com.career.careerpath.model.Speaker
import com.career.careerpath.view.adapter.SpeakersAdapter
import com.career.careerpath.view.adapter.SpeakersListener
import com.career.careerpath.viewmodel.SpeakersViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_speakers.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/*
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
 */

class SpeakersFragment : Fragment(), SpeakersListener {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var speakersAdapter: SpeakersAdapter
    private lateinit var viewModel: SpeakersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SpeakersViewModel::class.java)
        viewModel.refresh()

        speakersAdapter = SpeakersAdapter(this)
        rvSpeakers.apply {
            layoutManager = GridLayoutManager(view.context,2)
            adapter = speakersAdapter
        }

        observerViewModel()
    }


    @SuppressLint("FragmentLiveDataObserve")
    fun observerViewModel() {
        viewModel.listSpeaker.observe(this, Observer<List<Speaker>> { speaker ->
            speakersAdapter.ubdateData(speaker)
        })

        viewModel.isLoading.observe(this, Observer<Boolean> {
            if (it != null)
                rlBase.visibility = View.GONE

        })
    }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to Gson().toJson(speaker))
        findNavController().navigate(R.id.speakersDetailDialogFragment, bundle)
    }

    /*
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SpeakersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SpeakersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
     */
}