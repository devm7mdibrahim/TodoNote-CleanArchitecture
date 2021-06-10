package com.devM7mdibrahim.task.ui.todo

import androidx.fragment.app.viewModels
import com.devM7mdibrahim.task.R
import com.devM7mdibrahim.task.databinding.FragmentTodoBinding
import com.devM7mdibrahim.task.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoFragment : BaseFragment<FragmentTodoBinding>() {

    private val viewModel by viewModels<ToDoViewModel>()

    override fun getFragmentView(): Int = R.layout.fragment_todo


}