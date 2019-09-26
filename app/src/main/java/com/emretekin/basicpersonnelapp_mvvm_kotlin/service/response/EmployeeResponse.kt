package com.emretekin.basicpersonnelapp_mvvm_kotlin.service.response

import com.emretekin.basicpersonnelapp_mvvm_kotlin.db.EmployeeEntity
import com.google.gson.annotations.SerializedName

data class EmployeeResponse(

    @field:SerializedName("data")
    val employees: List<EmployeeEntity?>? = null,

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total")
    val total: Int? = null


)