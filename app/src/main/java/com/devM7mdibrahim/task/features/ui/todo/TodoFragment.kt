package com.devM7mdibrahim.task.features.ui.todo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.devM7mdibrahim.task.R
import com.devM7mdibrahim.task.databinding.FragmentTodoBinding
import com.devM7mdibrahim.task.domain.common.DataState
import com.devM7mdibrahim.task.features.base.BaseFragment
import com.devM7mdibrahim.task.features.model.TodoItem
import com.devM7mdibrahim.task.features.ui.home.HomeFragment.Companion.TodoBundle
import com.devM7mdibrahim.task.features.utils.toGone
import com.devM7mdibrahim.task.features.utils.toVisible
import com.devM7mdibrahim.task.features.notifications.TodoAlertWorker
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class TodoFragment : BaseFragment<FragmentTodoBinding>() {

    private val gson by lazy {
        Gson()
    }

    private var todoItem: TodoItem? = null

    private val viewModel by viewModels<TodoViewModel>()

    override fun getFragmentView(): Int = R.layout.fragment_todo

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val todoJson = it.getString(TodoBundle)
            todoItem = gson.fromJson(todoJson, TodoItem::class.java)
        }

        todoItem?.let {
            binding?.run {
                etTitle.setText(it.title)
                etBody.setText(it.body)
            }
        }

        binding?.btnSave?.setOnClickListener {
            if (todoItem != null) {
                editTodo()
            } else {
                addTodo()
            }
        }
    }

    private fun addTodo() {
        val data = getData()
        viewModel.insertTodoItem(
            TodoItem(
                title = data.title,
                body = data.body
            )
        )
        viewModel.todoInsert.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Loading -> {
                    binding?.progressBar?.toVisible()
                }

                is DataState.Success -> {
                    binding?.progressBar?.toGone()
                    showToast("note add successfully")
                    scheduleNotification()
                }

                is DataState.Error -> {
                    binding?.progressBar?.toGone()
                    showToast(it.exception)
                }
            }
        })
    }

    private fun scheduleNotification() {
        val work = OneTimeWorkRequest
            .Builder(TodoAlertWorker::class.java)
            .setInitialDelay(15, TimeUnit.MINUTES)
            .build()

        val workManager = WorkManager.getInstance(requireContext())
        workManager.enqueue(work)
    }

    private fun editTodo() {
        val data = getData()
        viewModel.updateTodoItem(
            TodoItem(
                todoItem?.id,
                data.title,
                data.body
            )
        )

        viewModel.todoUpdate.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Loading -> {
                    binding?.progressBar?.toVisible()
                }

                is DataState.Success -> {
                    binding?.progressBar?.toGone()
                    showToast("note edited successfully")
                }

                is DataState.Error -> {
                    binding?.progressBar?.toGone()
                    showToast(it.exception)
                }
            }
        })
    }

    private fun getData(): TodoItem {
        val mTitle = binding?.etTitle?.text.toString()
        val mBody = binding?.etBody?.text.toString()

        return TodoItem(
            title = mTitle,
            body = mBody
        )
    }
}