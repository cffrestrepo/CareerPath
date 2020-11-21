package com.career.careerpath.Application

import android.app.Application
import com.career.careerpath.injection.AplicationModule
import com.career.careerpath.injection.ApplicationComponent
import com.career.careerpath.injection.DaggerApplicationComponent


class ApplicationCareerPath : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .aplicationModule(AplicationModule(this))
            .build()

    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}