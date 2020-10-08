package com.career.careerpath.viewmodel

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.career.careerpath.model.Conference
import com.career.careerpath.model.Speaker
import com.career.careerpath.network.Callback
import com.career.careerpath.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel : ViewModel() {
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()
    var input = ObservableField<String>("")
    var cantidad = MutableLiveData<Int>()

    fun refresh() {
        getSpeakerFromFirebase()
    }

    init {
        input.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                cantidad.value = input.get()?.length
            }
        })
    }

    fun getSpeakerFromFirebase() {
        firestoreService.getSchedule(object : Callback<List<Conference>> {
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished() {
        isLoading.value = true
    }
}