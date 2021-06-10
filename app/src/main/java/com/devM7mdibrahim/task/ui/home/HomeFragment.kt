package com.devM7mdibrahim.task.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.devM7mdibrahim.task.R
import com.devM7mdibrahim.task.databinding.FragmentHomeBinding
import com.devM7mdibrahim.task.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun getFragmentView(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        getAllList()
    }

    private fun initRV() {
        binding?.rvTodos.apply {

        }
    }

    private fun getAllList() {
        viewModel.getAllList().observe(viewLifecycleOwner, {

        })
    }
}