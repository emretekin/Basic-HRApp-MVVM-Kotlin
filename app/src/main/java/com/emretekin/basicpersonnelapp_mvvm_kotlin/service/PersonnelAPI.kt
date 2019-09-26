package com.emretekin.basicpersonnelapp_mvvm_kotlin.service

import com.emretekin.basicpersonnelapp_mvvm_kotlin.service.response.EmployeeResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonnelAPI {

    @GET("/api/users?")
    fun getPersonnel(@Query("page") page: Int, @Query("delay") delay: Int, @Query("per_page") perPage: Int): Observable<EmployeeResponse>

}