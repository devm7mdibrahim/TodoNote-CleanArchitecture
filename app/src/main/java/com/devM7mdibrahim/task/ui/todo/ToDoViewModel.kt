package com.devM7mdibrahim.task.ui.todo

import androidx.lifecycle.ViewModel
import com.devM7mdibrahim.task.domain.interactors.AddToDoUseCase
import com.devM7mdibrahim.task.domain.interactors.UpdateToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val updateToDoUseCase: UpdateToDoUseCase,
    private val addToDoUseCase: AddToDoUseCase,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}