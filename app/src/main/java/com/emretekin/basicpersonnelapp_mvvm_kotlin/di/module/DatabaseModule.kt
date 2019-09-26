package com.emretekin.basicpersonnelapp_mvvm_kotlin.di.module

import android.content.Context
import androidx.room.Room
import com.emretekin.basicpersonnelapp_mvvm_kotlin.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "personnelapp-db"
        ).build()
    }
}