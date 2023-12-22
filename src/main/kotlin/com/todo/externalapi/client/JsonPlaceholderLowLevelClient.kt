package com.todo.externalapi.client

import com.todo.externalapi.model.User
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpHeaders.ACCEPT
import io.micronaut.http.HttpHeaders.USER_AGENT
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.uri.UriBuilder
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import java.net.URI

@Singleton
class JsonPlaceholderLowLevelClient(
    private val httpClient: HttpClient
) {
    private val usersUri: URI by lazy { UriBuilder.of("/users").build() }
    fun fetchUsers(): Publisher<MutableList<User>> {
        val httpRequest: HttpRequest<*> = HttpRequest.GET<Any>(usersUri)
            .header(USER_AGENT, "Micronaut HTTP Client")
            .header(ACCEPT, "application/json")

        return httpClient.retrieve(httpRequest, Argument.listOf(User::class.java))
    }
}