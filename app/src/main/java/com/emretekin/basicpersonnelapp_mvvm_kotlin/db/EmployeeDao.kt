package com.emretekin.basicpersonnelapp_mvvm_kotlin.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update
import androidx.room.Query


@Dao
interface EmployeeDao {

    @Query("SELECT * FROM Employees")
    fun getEmployees(): LiveData<List<EmployeeEntity>>

    @Query("SELECT * FROM Employees WHERE id = :id")
    fun getEmployee(id: Int): EmployeeEntity

    @Insert(onConflict = REPLACE)
    fun insertEmployee(employee: EmployeeEntity?)

    @Update
    fun updateEmployee(employee: EmployeeEntity)
}