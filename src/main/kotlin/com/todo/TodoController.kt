package com.todo

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus

@Controller("/todo")
class TodoController {

    @Get(uri="/", produces=["text/plain"])
    fun index(): String {
        return "Example Response"
    }
}