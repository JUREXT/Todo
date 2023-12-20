package com.todo.controller

import com.todo.entity.Todo
import com.todo.repository.TaskRepository
import com.todo.repository.TodoRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Valid

@Validated
@Controller("/todos")
class TodoController {

    @Inject
    lateinit var todoRepository: TodoRepository

    @Inject
    lateinit var taskRepository: TaskRepository

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): HttpResponse<Any> = HttpResponse.status<Any>(HttpStatus.OK).body(todoRepository.findAll())

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    fun create(@Body todo: @Valid Todo): HttpResponse<Any> {
        val savedTodo = todoRepository.save(todo)
        return HttpResponse.status<Any>(HttpStatus.CREATED).body(savedTodo)
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathVariable("id") id: Long): HttpResponse<Any> {
        val optionalTodo = todoRepository.findById(id)
        return if (optionalTodo.isPresent) {
            HttpResponse.status<Any>(HttpStatus.OK).body(optionalTodo.get())
        } else {
            HttpResponse.status(HttpStatus.NOT_FOUND)
        }
    }

    @Put("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun update(@PathVariable("id") id: Long, @Body @Valid todo: Todo): HttpResponse<Any> {
        val existingTodoOptional = todoRepository.findById(id)

        return if (existingTodoOptional.isPresent) {
            val existingTodo = existingTodoOptional.get()
            existingTodo.name = todo.name
            existingTodo.description = todo.description

            val updatedTodo = todoRepository.update(existingTodo)
            HttpResponse.status<Any>(HttpStatus.OK).body(updatedTodo)
        } else {
            HttpResponse.status(HttpStatus.NOT_FOUND)
        }
    }

    @Delete("/{id}")
    fun deleteById(@PathVariable("id") id: Long): HttpResponse<Any> {
        val todoOptional = todoRepository.findById(id)

        return if (todoOptional.isPresent) {
            todoRepository.delete(todoOptional.get())
            HttpResponse.status(HttpStatus.OK)
        } else {
            HttpResponse.status(HttpStatus.NOT_FOUND)
        }
    }

    @Error(exception = ConstraintViolationException::class)
    fun handleConstraintViolationException(e: ConstraintViolationException): HttpResponse<Any> {
        return HttpResponse.badRequest("Todo Controller Error Message: ${e.message}")
    }
}