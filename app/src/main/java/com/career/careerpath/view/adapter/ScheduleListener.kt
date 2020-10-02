package com.career.careerpath.view.adapter

import android.telecom.Conference

interface ScheduleListener {

    fun onConferenceClicked(conference: Conference, position:Int)

}