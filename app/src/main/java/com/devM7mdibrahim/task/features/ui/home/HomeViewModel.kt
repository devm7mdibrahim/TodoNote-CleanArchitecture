package com.devM7mdibrahim.task.features.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devM7mdibrahim.task.domain.common.DataState
import com.devM7mdibrahim.task.domain.interactors.DeleteTodoUseCase
import com.devM7mdibrahim.task.domain.interactors.GetAllTodosUseCase
import com.devM7mdibrahim.task.features.mapper.TodoMapper
import com.devM7mdibrahim.task.features.model.TodoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val compositeDisposable: CompositeDisposable,
    private val mapper: TodoMapper
) : ViewModel() {

    private val _todosList = MutableLiveData<DataState<List<TodoItem>>>()
    val todoList: LiveData<DataState<List<TodoItem>>>
        get() = _todosList

    fun getAllList() {
        _todosList.postValue(DataState.Loading)
        compositeDisposable.add(
            getAllTodosUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ todos -> _todosList.postValue(DataState.Success(todos.map { toDoModel ->
                        mapper.mapToView(
                            toDoModel
                        )
                    }))
                }) { error -> _todosList.postValue(DataState.Error(exception = error.message.toString())) }
        )
    }


    private val _todoDeleted = MutableLiveData<DataState<Boolean>>()
    val todoDeleted : LiveData<DataState<Boolean>>
            get() = _todoDeleted

    fun deleteTodoItem(todo: TodoItem) {
        _todoDeleted.postValue(DataState.Loading)
        compositeDisposable.add(
            deleteTodoUseCase.invoke(mapper.mapFromView(todo))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _todoDeleted.postValue(DataState.Success(true))
                }) { error -> _todoDeleted.postValue(DataState.Error(exception = error.message.toString())) }
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}