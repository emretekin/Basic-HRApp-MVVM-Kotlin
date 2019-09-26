package com.emretekin.basicpersonnelapp_mvvm_kotlin.di.component

import android.content.Context
import android.content.SharedPreferences
import com.emretekin.basicpersonnelapp_mvvm_kotlin.PersonnelApp
import com.emretekin.basicpersonnelapp_mvvm_kotlin.di.module.ApplicationModule
import com.emretekin.basicpersonnelapp_mvvm_kotlin.di.module.DatabaseModule
import com.emretekin.basicpersonnelapp_mvvm_kotlin.di.module.NetModule
import com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.main.MainViewModel
import com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.adapter.EmployeesAdapterItemViewModel
import com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.detail.EmployeeDetailViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton

@Component(modules = [ApplicationModule::class, NetModule::class, DatabaseModule::class])

interface ApplicationComponent {

    fun app(): PersonnelApp

    fun context(): Context

    fun preferences(): SharedPreferences

    fun inject(mainViewModel: MainViewModel)
    
    fun inject(employeesViewModel: EmployeesAdapterItemViewModel)

    fun inject(employeeDetailViewModel: EmployeeDetailViewModel)

}