package com.todo.externalapi.client

import com.todo.externalapi.model.User
import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.HttpHeaders.ACCEPT
import io.micronaut.http.HttpHeaders.USER_AGENT
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Headers
import io.micronaut.http.client.annotation.Client

@Client(id = "https://jsonplaceholder.typicode.com")
@Headers(
    Header(name = USER_AGENT, value = "Micronaut HTTP Client"),
    Header(name = ACCEPT, value = "application/json")
)
interface JsonPlaceholderApiClient {
//    @Get("/users")
//    @SingleResult
//    fun fetchUsers(): List<User>

    @Get("/users")
    @SingleResult
    fun getUsers(): List<User>
}