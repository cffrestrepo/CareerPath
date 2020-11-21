package com.career.careerpath.injection

import com.career.careerpath.view.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AplicationModule::class)])
public interface ApplicationComponent {

    public fun mainActivity(mainActivity : MainActivity)

}