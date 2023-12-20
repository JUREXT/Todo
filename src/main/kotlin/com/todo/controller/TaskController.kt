package com.todo.controller

import com.todo.repository.TaskRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import jakarta.validation.ConstraintViolationException

@Validated
@Controller("/tasks")
class TaskController {

    @Inject
    lateinit var taskRepository: TaskRepository

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): HttpResponse<Any> = HttpResponse.status<Any>(HttpStatus.OK).body(taskRepository.findAll())

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathVariable("id") id: Long): HttpResponse<Any> {
        val optionalTask = taskRepository.findById(id)

        return if (optionalTask.isPresent) {
            HttpResponse.status<Any>(HttpStatus.OK).body(optionalTask.get())
        } else {
            HttpResponse.status(HttpStatus.NOT_FOUND)
        }
    }

    @Delete("/{id}")
    fun deleteById(@PathVariable("id") id: Long): HttpResponse<Any> {
        val taskOptional = taskRepository.findById(id)

        return if (taskOptional.isPresent) {
            taskRepository.delete(taskOptional.get())
            HttpResponse.status(HttpStatus.OK)
        } else {
            HttpResponse.status(HttpStatus.NOT_FOUND)
        }
    }

    @Error(exception = ConstraintViolationException::class)
    fun handleConstraintViolationException(e: ConstraintViolationException): HttpResponse<Any> {
        return HttpResponse.badRequest("Task Controller Error Message: ${e.message}")
    }
}