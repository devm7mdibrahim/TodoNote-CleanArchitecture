package com.devM7mdibrahim.task.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devM7mdibrahim.task.domain.common.DataState
import com.devM7mdibrahim.task.domain.interactors.DeleteToDoUseCase
import com.devM7mdibrahim.task.domain.interactors.GetAllToDosUseCase
import com.devM7mdibrahim.task.domain.model.ToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllToDosUseCase: GetAllToDosUseCase,
    private val deleteToDoUseCase: DeleteToDoUseCase,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    fun getAllList(): LiveData<DataState<List<ToDo>>> {
        val todosList = MutableLiveData<DataState<List<ToDo>>>()
        compositeDisposable.add(
            getAllToDosUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ todos ->
                    todosList.postValue(DataState.Success(todos))
                }) { error -> todosList.postValue(DataState.Error(exception = error.message.toString())) }
        )
        return todosList
    }

    fun deleteTodoItem(toDo: ToDo): LiveData<DataState<Boolean>>{
        val todoDeleted = MutableLiveData<DataState<Boolean>>()
        compositeDisposable.add(
            deleteToDoUseCase.invoke(toDo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    todoDeleted.postValue(DataState.Success(true))
                }) { error -> todoDeleted.postValue(DataState.Error(exception = error.message.toString())) }
        )

        return todoDeleted
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }
}