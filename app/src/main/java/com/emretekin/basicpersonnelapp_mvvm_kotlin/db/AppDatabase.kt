package com.emretekin.basicpersonnelapp_mvvm_kotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmployeeEntity::class], version = 3
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
}