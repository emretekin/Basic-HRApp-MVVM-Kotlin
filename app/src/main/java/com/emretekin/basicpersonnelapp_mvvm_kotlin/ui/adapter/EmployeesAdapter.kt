package com.emretekin.basicpersonnelapp_mvvm_kotlin.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.emretekin.basicpersonnelapp_mvvm_kotlin.R
import com.emretekin.basicpersonnelapp_mvvm_kotlin.core.BaseAdapter
import com.emretekin.basicpersonnelapp_mvvm_kotlin.databinding.RowEmployeeItemBinding
import com.emretekin.basicpersonnelapp_mvvm_kotlin.db.EmployeeEntity
import com.squareup.picasso.Picasso

import org.jetbrains.anko.doAsync


class EmployeesAdapter(private val clickCallback: ((EmployeeEntity) -> Unit)?) :
    BaseAdapter<EmployeeEntity>(object : DiffUtil.ItemCallback<EmployeeEntity>() {

        override fun areItemsTheSame(oldItem: EmployeeEntity, newItem: EmployeeEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: EmployeeEntity, newItem: EmployeeEntity): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val viewModel = EmployeesAdapterItemViewModel((parent.context as Activity).application)
        val binding: RowEmployeeItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_employee_item, parent, false
        )
        binding.viewModel = viewModel
        binding.rootView.setOnClickListener {
            binding.viewModel?.item?.get()?.let {
                clickCallback?.invoke(it)
            }
        }

        return binding
    }

    @SuppressLint("SetTextI18n")
    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as RowEmployeeItemBinding).viewModel?.item?.set(getItem(position))
        val employeeEntity = getItem(position)

        if (!employeeEntity?.avatar.isNullOrEmpty()){
            Picasso.get().load(employeeEntity.avatar).into(binding.employeeImageview)
        }

        binding.executePendingBindings()
    }
}