package com.todo.repository

import com.todo.entity.Task
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface TaskRepository : CrudRepository<Task?, Long?>