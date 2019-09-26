package com.emretekin.basicpersonnelapp_mvvm_kotlin.db

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Created by emretekin on 16.05.2019
 */

@Entity(tableName = "Employees")
data class EmployeeEntity(
    @PrimaryKey
    var id: Int,
    var email: String?,
    var first_name: String?,
    var last_name: String?,
    var avatar: String?
) {
}