package com.emretekin.basicpersonnelapp_mvvm_kotlin.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.emretekin.basicpersonnelapp_mvvm_kotlin.PersonnelApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(var app: PersonnelApp) {

    @Provides
    @Singleton
    fun provideApp(): PersonnelApp = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)

}