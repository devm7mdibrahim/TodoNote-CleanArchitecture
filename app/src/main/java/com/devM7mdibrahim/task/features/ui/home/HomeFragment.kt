package com.devM7mdibrahim.task.features.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devM7mdibrahim.task.R
import com.devM7mdibrahim.task.databinding.FragmentHomeBinding
import com.devM7mdibrahim.task.domain.common.DataState
import com.devM7mdibrahim.task.features.base.BaseFragment
import com.devM7mdibrahim.task.features.model.TodoItem
import com.devM7mdibrahim.task.features.utils.toGone
import com.devM7mdibrahim.task.features.utils.toVisible
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val gson by lazy {
        Gson()
    }

    private val list = mutableListOf<TodoItem>()

    private val viewModel by viewModels<HomeViewModel>()

    private val todosAdapter by lazy {
        ToDoAdapter {
            val bundle = bundleOf(TodoBundle to gson.toJson(it))
            requireView().findNavController()
                .navigate(R.id.action_homeFragment_to_toDoFragment, bundle)
        }
    }

    override fun getFragmentView(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        getAllList()


        binding?.fab?.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_homeFragment_to_toDoFragment)
        }
    }

    private fun initRV() {
        binding?.rvTodos?.apply {
            adapter = todosAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }.also {
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val toDoItem = list[position]
                    deleteItem(toDoItem)
                }
            }).attachToRecyclerView(it)
        }
    }

    private fun getAllList() {
        viewModel.getAllList()
        viewModel.todoList.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Loading -> {

                }

                is DataState.Success -> {
                    binding?.progressBar?.toGone()
                    list.clear()
                    list.addAll(it.data)
                    todosAdapter.submitList(it.data)
                }

                is DataState.Error -> {
                    binding?.progressBar?.toGone()
                    showToast(it.exception)
                }
            }
        })
    }

    private fun deleteItem(todoItem: TodoItem) {
        viewModel.deleteTodoItem(todoItem)
        viewModel.todoDeleted.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Loading -> {
                    binding?.progressBar?.toVisible()
                }

                is DataState.Success -> {
                    binding?.progressBar?.toGone()
                    list.remove(todoItem)
                    todosAdapter.submitList(list)
                    showToast("todo deleted successfully")
                }

                is DataState.Error -> {
                    binding?.progressBar?.toGone()
                    showToast(it.exception)
                }
            }
        })
    }

    companion object {
        const val TodoBundle = "todo"
    }
}