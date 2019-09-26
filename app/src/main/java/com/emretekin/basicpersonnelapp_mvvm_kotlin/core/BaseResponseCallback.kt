package com.emretekin.basicpersonnelapp_mvvm_kotlin.core

interface BaseResponseCallback<T> {

    fun onSuccess(data: T)
    fun onFail(message: String)
}