package com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.detail

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.emretekin.basicpersonnelapp_mvvm_kotlin.PersonnelApp
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.BaseViewModel
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.Resource
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.Status
import com.emretekin.basicpersonnelapp_mvvm_kotlin.db.EmployeeEntity
import com.emretekin.basicpersonnelapp_mvvm_kotlin.service.response.EmployeeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import timber.log.Timber

class EmployeeDetailViewModel(app: Application) : BaseViewModel(app) {

    lateinit var employee: EmployeeEntity

    val emplooyeLiveData = MutableLiveData<EmployeeEntity>()

    var name: ObservableField<String> = ObservableField("")
    var surname: ObservableField<String> = ObservableField("")
    var email: ObservableField<String> = ObservableField("")

    init {
        (app as? PersonnelApp)?.component?.inject(this)
    }

    fun getEmployee(id: Int) {
        if (id == -1){
            return
        }
        doAsync {
            employee = db.employeeDao().getEmployee(id)

            doAsyncResult {
                emplooyeLiveData.postValue(employee)
                name.set(employee.first_name)
                surname.set(employee.last_name)
                email.set(employee.email)
            }
        }


    }
}