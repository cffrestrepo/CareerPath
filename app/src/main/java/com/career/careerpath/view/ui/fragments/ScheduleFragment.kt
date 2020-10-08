package com.career.careerpath.view.ui.fragments

import android.R.attr.data
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.career.careerpath.R
import com.career.careerpath.databinding.FragmentScheduleBinding
import com.career.careerpath.model.Conference
import com.career.careerpath.view.adapter.ScheduleAdapter
import com.career.careerpath.view.adapter.ScheduleListener
import com.career.careerpath.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ScheduleFragment : Fragment(), ScheduleListener {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var scheduleAdpater: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel
    private lateinit var binding: FragmentScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
         */
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_schedule, container, false
        )
        val view: View = binding.getRoot()
        //here data must be an instance of the class MarsDataProvider
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()

        binding.viewmodel = viewModel

        scheduleAdpater = ScheduleAdapter(this)
        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = scheduleAdpater
        }

        observerViewModel()
    }

    @SuppressLint("FragmentLiveDataObserve")
    fun observerViewModel() {
        viewModel.listSchedule.observe(this, Observer<List<Conference>> { schedule ->
            scheduleAdpater.ubdateData(schedule)
        })

        viewModel.isLoading.observe(this, Observer<Boolean> {
            if (it != null)
                rlLoadingSchedule.visibility = View.GONE
        })

        viewModel.cantidad.observe(this, Observer<Int>{
            tvCantidad.text = it.toString()
        })
    }

    override fun onConferenceClicked(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference)
        findNavController().navigate(R.id.scheduleDetailDialogFragment, bundle)
    }

    /*
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
     */
}