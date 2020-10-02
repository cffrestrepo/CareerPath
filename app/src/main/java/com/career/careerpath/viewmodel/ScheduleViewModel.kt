package com.career.careerpath.viewmodel

import androidx.lifecycle.MutableLiveData
import com.career.careerpath.model.Conference
import com.career.careerpath.model.Speaker
import com.career.careerpath.network.Callback
import com.career.careerpath.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel {
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakerFromFirebase()
    }

    fun getSpeakerFromFirebase() {
        firestoreService.getSpeakers(object:Callback<List<Speaker>>{
            override fun onSuccess(result: List<Speaker>?) {
                listSchedule.postValue(result)
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