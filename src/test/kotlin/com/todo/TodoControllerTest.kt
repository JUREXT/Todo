package com.todo

import com.todo.controller.TodoController
import com.todo.entity.Todo
import com.todo.repository.TodoRepository
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@MicronautTest
class TodoControllerTest {

    @Inject
    lateinit var repository: TodoRepository

    @Inject
    lateinit var todoController: TodoController


    @Test
    fun testCreateAndGetById() {
        val todo = Todo(id = 1, name = "Test Todo", description = "Test Description")

        val savedTodo = repository.save(todo)
        val retrievedTodo = repository.findById(savedTodo.id)

        Assertions.assertTrue(retrievedTodo.isPresent)
        Assertions.assertEquals(savedTodo.id, retrievedTodo.get().id)
        Assertions.assertEquals(todo.name, retrievedTodo.get().name)
        Assertions.assertEquals(todo.description, retrievedTodo.get().description)
    }

    @Test
    fun testGetByIdIsHttpStatusOK() {
        // Given
        val todo = Todo(
            id = 1,
            name = "Todo Name",
            description = "Todo Desc",
            tasks = emptyList()
        )

        repository.save(todo)

        // When
        val httpOKStatus = todoController.getById(id = 1)

        // Then
        Assertions.assertEquals(httpOKStatus.status, HttpStatus.OK)
    }
}