package com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.adapter

import android.app.Application
import android.util.Log
import androidx.appcompat.view.SupportActionModeWrapper
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.emretekin.basicpersonnelapp_mvvm_kotlin.PersonnelApp
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.BaseViewModel
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.Resource
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.Status
import com.emretekin.basicpersonnelapp_mvvm_kotlin.db.EmployeeEntity
import com.emretekin.basicpersonnelapp_mvvm_kotlin.service.PersonnelAPI
import com.emretekin.basicpersonnelapp_mvvm_kotlin.service.response.EmployeeResponse
import com.uber.autodispose.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import timber.log.Timber
import javax.inject.Inject

class EmployeesAdapterItemViewModel(app: Application) : BaseViewModel(app) {

    var item: ObservableField<EmployeeEntity> = ObservableField()


    init {
        (app as? PersonnelApp)?.component?.inject(this)
    }



}