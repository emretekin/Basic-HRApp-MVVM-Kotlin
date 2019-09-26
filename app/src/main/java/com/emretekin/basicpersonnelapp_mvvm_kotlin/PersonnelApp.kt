package com.emretekin.basicpersonnelapp_mvvm_kotlin

import android.app.Application
import com.emretekin.basicpersonnelapp_mvvm_kotlin.di.component.DaggerApplicationComponent
import com.emretekin.basicpersonnelapp_mvvm_kotlin.di.module.ApplicationModule
import com.facebook.stetho.Stetho

class PersonnelApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}

