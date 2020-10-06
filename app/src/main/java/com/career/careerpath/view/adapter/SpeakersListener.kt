package com.career.careerpath.view.adapter

import com.career.careerpath.model.Speaker

interface SpeakersListener {

    fun onSpeakerClicked(speaker: Speaker, position:Int)

}