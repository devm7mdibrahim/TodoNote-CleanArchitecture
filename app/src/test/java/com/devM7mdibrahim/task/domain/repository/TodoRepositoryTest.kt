package com.devM7mdibrahim.task.domain.repository

import com.devM7mdibrahim.task.data.datasource.FakeLocalDataSource
import com.devM7mdibrahim.task.data.mapper.TodoMapper
import com.devM7mdibrahim.task.data.repository.TodoRepositoryImpl
import com.devM7mdibrahim.task.domain.model.TodoModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock


class TodoRepositoryTest {
    private var todo1 = TodoModel(1, "Title1", "Description1")
    private val todo2 = TodoModel(2, "Title2", "Description2")
    private val todo3 = TodoModel(3, "Title3", "Description3")
    private val allTodos = listOf(todo1, todo2, todo3).sortedBy { it.id }
    private val localTodos = listOf(todo1, todo2).sortedBy { it.id }
    private val newTodos = listOf(todo3).sortedBy { it.id }

    private lateinit var todoLocalDataSource: FakeLocalDataSource

    private lateinit var todoRepository: TodoRepositoryImpl
    private lateinit var todoMapper: TodoMapper

    @Before
    fun init() {
        todoMapper = mock(TodoMapper::class.java)

        todoLocalDataSource = FakeLocalDataSource(localTodos.map {
            todoMapper.mapFromDomain(it)
        }.toMutableList())

        todoRepository = TodoRepositoryImpl(todoLocalDataSource, todoMapper)
    }

    @Test
    fun getAllTodos() {
        todoRepository.getTodos()
            .test()
            .assertValue {
                return@assertValue it == localTodos
            }
    }

    @Test
    fun saveNewTodoAndRequestAllTodos() {
        newTodos.forEach {
            todoRepository.insertTodo(it).blockingAwait()
        }

        todoRepository.getTodos()
            .test()
            .assertValue {
                return@assertValue it == allTodos
            }
    }

    @Test
    fun deleteTodoAndRequestAllTodos() {
        todoRepository.insertTodo(todo1).blockingAwait()

        todoRepository.getTodos()
            .test()
            .assertValue {
                return@assertValue it == localTodos
            }
    }


    @Test
    fun editTodoAndRequestAllTodos() {
        val editedNote =TodoModel(1, "Title1 edited", "Description1 edited")
        todo1 = editedNote
        todoRepository.updateTodo(todo1).blockingAwait()

        todoRepository.getTodos()
            .test()
            .assertValue {
                return@assertValue it == localTodos
            }
    }
}