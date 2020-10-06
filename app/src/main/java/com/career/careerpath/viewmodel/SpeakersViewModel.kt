package com.career.careerpath.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.career.careerpath.model.Conference
import com.career.careerpath.model.Speaker
import com.career.careerpath.network.Callback
import com.career.careerpath.network.FirestoreService
import java.lang.Exception

class SpeakersViewModel : ViewModel(){
    val firestoreService = FirestoreService()
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase() {
        firestoreService.getSpeakers(object:Callback<List<Speaker>>{
            override fun onSuccess(result: List<Speaker>?) {
                listSpeaker.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished(){
        isLoading.value = true
    }
}