package com.devM7mdibrahim.task.features.ui.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devM7mdibrahim.task.domain.common.DataState
import com.devM7mdibrahim.task.domain.interactors.AddTodoUseCase
import com.devM7mdibrahim.task.domain.interactors.UpdateTodoUseCase
import com.devM7mdibrahim.task.features.mapper.TodoMapper
import com.devM7mdibrahim.task.features.model.TodoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val compositeDisposable: CompositeDisposable,
    private val mapper: TodoMapper
) : ViewModel() {

    private val _todoInsert = MutableLiveData<DataState<Boolean>>()
    val todoInsert: LiveData<DataState<Boolean>>
        get() = _todoInsert

    fun insertTodoItem(todo: TodoItem) {
        if (todo.title.isNullOrEmpty()){
            _todoInsert.postValue(DataState.Error(exception = "title cannot be null or empty"))
            return
        }
        if (todo.body.isNullOrEmpty()){
            _todoInsert.postValue(DataState.Error(exception = "body cannot be null or empty"))
            return
        }

        compositeDisposable.add(
            addTodoUseCase.invoke(mapper.mapFromView(todo))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _todoInsert.postValue(DataState.Success(true))
                }) { error -> _todoInsert.postValue(DataState.Error(exception = error.message.toString())) }
        )
    }

    private val _todoUpdate = MutableLiveData<DataState<Boolean>>()
    val todoUpdate: LiveData<DataState<Boolean>>
        get() = _todoUpdate

    fun updateTodoItem(todo: TodoItem) {
        if (todo.title.isNullOrEmpty()){
            _todoUpdate.postValue(DataState.Error(exception = "title cannot be null or empty"))
            return
        }
        if (todo.body.isNullOrEmpty()){
            _todoUpdate.postValue(DataState.Error(exception = "body cannot be null or empty"))
            return
        }
        compositeDisposable.add(
            updateTodoUseCase.invoke(mapper.mapFromView(todo))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _todoUpdate.postValue(DataState.Success(true))
                }) { error -> _todoUpdate.postValue(DataState.Error(exception = error.message.toString())) }
        )

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}