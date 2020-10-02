package com.career.careerpath.view.adapter

import com.career.careerpath.model.Conference


interface ScheduleListener {

    fun onConferenceClicked(conference: Conference, position:Int)

}