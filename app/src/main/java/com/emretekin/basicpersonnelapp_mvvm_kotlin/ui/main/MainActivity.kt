package com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emretekin.basicpersonnelapp_mvvm_kotlin.R
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.BaseActivity
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.Resource
import com.emretekin.basicpersonnelapp_mvvm_kotlin.databinding.ActivityMainBinding
import com.emretekin.basicpersonnelapp_mvvm_kotlin.service.response.EmployeeResponse
import com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.adapter.EmployeesAdapter
import com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.detail.EmployeeDetailActivity
import com.emretekin.basicpersonnelapp_mvvm_kotlin.utils.Constants
import com.emretekin.basicpersonnelapp_mvvm_kotlin.utils.PaginationScrollListener
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.runOnUiThread

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(
    MainViewModel::class.java
) {

    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var nextPage: Int? = null
    var perPage = 10
    var delayTime = 1
    var totalEpisodes: Int? = null

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun initViewModel(viewModel: MainViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getEmployees(0, delayTime, perPage)

        setToolbar()
        setEmployeeItems()
        createObservers()
    }

    fun createObservers() {
        viewModel.emplooyesLiveData.observe(this, Observer<Resource<EmployeeResponse>> {
            viewModel.insertEmployeesToDb()
            (binding.employeesRecyclerview.adapter as EmployeesAdapter).submitList(it.data?.employees)
        })

        viewModel.db.employeeDao().getEmployees().observe(this, Observer {
            (binding.employeesRecyclerview.adapter as EmployeesAdapter).submitList(it)
        })
    }

    fun setEmployeeItems() {

        val adapter = EmployeesAdapter {
            val nextScreenIntent = Intent(this.applicationContext, EmployeeDetailActivity::class.java)
            nextScreenIntent.putExtra(Constants.BundleArguments.EMPLOYEE_ID, it.id)
            this.startActivity(nextScreenIntent)
            this.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left)
        }

        val layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        binding.employeesRecyclerview.layoutManager = layoutManager
        binding.employeesRecyclerview.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                if (totalEpisodes != layoutManager.itemCount)
                    getMoreItems(nextPage?.plus(1) ?: 0)
            }
        })

        binding.employeesRecyclerview.adapter = adapter
    }

    fun getMoreItems(mNextPage: Int) {
        viewModel.getEmployees(mNextPage, delayTime, perPage)

        if (viewModel.emplooyesLiveData.hasActiveObservers())
            viewModel.emplooyesLiveData.removeObservers(this)

        viewModel.emplooyesLiveData.observe(this,
            Observer<Resource<EmployeeResponse>> {
                totalEpisodes = it.data?.total
                nextPage = it.data?.page
                delayTime.plus(2)
            }
        )
    }

    fun setToolbar(){
        binding.toolbarMain.setTitle(getString(R.string.app_name))
    }
}
