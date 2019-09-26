package com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.BaseActivity
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.Resource
import com.emretekin.basicpersonnelapp_mvvm_kotlin.databinding.ActivityEmployeeDetailBinding
import com.emretekin.basicpersonnelapp_mvvm_kotlin.db.EmployeeEntity
import com.emretekin.basicpersonnelapp_mvvm_kotlin.service.response.EmployeeResponse
import com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.adapter.EmployeesAdapter
import com.emretekin.basicpersonnelapp_mvvm_kotlin.utils.Constants
import com.squareup.picasso.Picasso
import org.jetbrains.anko.doAsync
import timber.log.Timber
import android.R
import android.view.Menu
import android.view.MenuItem


class EmployeeDetailActivity :
    BaseActivity<EmployeeDetailViewModel, ActivityEmployeeDetailBinding>(EmployeeDetailViewModel::class.java) {

    override fun getLayoutRes(): Int = com.emretekin.basicpersonnelapp_mvvm_kotlin.R.layout.activity_employee_detail

    override fun initViewModel(viewModel: EmployeeDetailViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbar()
        viewModel.getEmployee(getIntentExtra())
        viewModel.emplooyeLiveData.observe(this, Observer<EmployeeEntity> {
            Picasso.get().load(it?.avatar).into(binding.employeeImageview)
        })

    }

    fun setToolbar(){
        binding.toolbarDetail.setTitle(getString(com.emretekin.basicpersonnelapp_mvvm_kotlin.R.string.employee_detail))
        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun getIntentExtra(): Int {
        val bundle: Bundle? = intent.extras
        val employeeId = bundle!!.getInt(Constants.BundleArguments.EMPLOYEE_ID, -1)
        return employeeId
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(com.emretekin.basicpersonnelapp_mvvm_kotlin.R.anim.enter_from_right, com.emretekin.basicpersonnelapp_mvvm_kotlin.R.anim.exit_out_right)
    }
}
