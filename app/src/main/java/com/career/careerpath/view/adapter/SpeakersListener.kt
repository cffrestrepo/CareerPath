package com.career.careerpath.view.adapter

import com.career.careerpath.model.Speaker

interface SpeakersListener {

    fun onConferenceClicked(speaker: Speaker, position:Int)

}