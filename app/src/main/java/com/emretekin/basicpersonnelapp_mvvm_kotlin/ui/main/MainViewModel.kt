package com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.main

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.emretekin.basicpersonnelapp_mvvm_kotlin.PersonnelApp
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.BaseViewModel
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.Resource
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.Status
import com.emretekin.basicpersonnelapp_mvvm_kotlin.service.response.EmployeeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import timber.log.Timber

class MainViewModel(app: Application) : BaseViewModel(app) {

    val emplooyesLiveData = MutableLiveData<Resource<EmployeeResponse>>()
    var progressLiveData = MutableLiveData<Boolean>()

    private val disposable = CompositeDisposable()

    init {
        (app as? PersonnelApp)?.component?.inject(this)
    }

    fun getEmployees(page: Int, delay: Int, perPage: Int) {
        disposable.add(
            baseApi.getPersonnel(page, delay, perPage)
                .subscribeOn(Schedulers.io())
                .map { Resource.success(it) }
                .onErrorReturn { Resource.error(it) }
                .doOnSubscribe { progressLiveData.postValue(true) }
                .doOnTerminate { progressLiveData.postValue(false) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    when (it?.status) {
                        Status.SUCCESS -> {
                            emplooyesLiveData.postValue(it)
                            insertEmployeesToDb()
                        }

                        Status.LOADING -> ""
                        Status.ERROR -> Timber.e(it.error)
                    }
                }
        )
    }

    fun insertEmployeesToDb() {
        doAsync {
            emplooyesLiveData.value?.data?.employees?.forEach {
                db.employeeDao().insertEmployee(it)
            }
        }
    }
}