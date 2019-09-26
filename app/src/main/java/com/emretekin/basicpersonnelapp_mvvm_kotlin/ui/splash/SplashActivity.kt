package com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.emretekin.basicpersonnelapp_mvvm_kotlin.R
import com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.detail.EmployeeDetailActivity
import com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.main.MainActivity
import com.emretekin.basicpersonnelapp_mvvm_kotlin.utils.Constants

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = Handler()
        val runnable = Runnable {
            val nextScreenIntent = Intent(this.applicationContext, MainActivity::class.java)
            this.startActivity(nextScreenIntent)
            this.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left)
            this.finish()
        }
        handler.postDelayed(runnable, 2000)
    }
}
