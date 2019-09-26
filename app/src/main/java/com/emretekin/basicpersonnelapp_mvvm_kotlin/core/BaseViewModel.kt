package com.emretekin.basicpersonnelapp_mvvm_kotlin.core

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import com.emretekin.basicpersonnelapp_mvvm_kotlin.db.AppDatabase
import com.emretekin.basicpersonnelapp_mvvm_kotlin.service.PersonnelAPI
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel(var app: Application): AndroidViewModel(app) {

    @Inject
    lateinit var pref: SharedPreferences

    @Inject
    lateinit var db: AppDatabase

    @Inject
    lateinit var baseApi: PersonnelAPI


}