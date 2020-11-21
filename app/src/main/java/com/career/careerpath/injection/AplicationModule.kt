package com.career.careerpath.injection

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
public class AplicationModule(private val aplication: Application) {

    @Provides
    @Singleton
    public fun provideContextApp() = aplication

}