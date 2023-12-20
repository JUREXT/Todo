package com.todo.repository

import com.todo.entity.Todo
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface TodoRepository : CrudRepository<Todo?, Long?>