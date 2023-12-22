package com.todo.externalapi

import com.todo.externalapi.client.JsonPlaceholderApiClient
import com.todo.externalapi.client.JsonPlaceholderLowLevelClient
import com.todo.externalapi.model.User
import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import jakarta.inject.Inject
import org.reactivestreams.Publisher

@Controller("/jsonPlaceholder")
class JsonPlaceholderController(
   // @Inject private val jsonPlaceholderLowLevelClient: JsonPlaceholderLowLevelClient,
  //  @Inject private val jsonPlaceholderApiClient: JsonPlaceholderApiClient
) {

    @Inject
    lateinit var jsonPlaceholderApiClient: JsonPlaceholderApiClient

//    @Get("/users")
//    @SingleResult
//    fun getUsersWithLowLevelClient(): Publisher<MutableList<User>> {
//        return jsonPlaceholderLowLevelClient.fetchUsers()
//    }


    @Produces(MediaType.APPLICATION_JSON)
    @SingleResult
    fun getUsers(): List<User> {
        return jsonPlaceholderApiClient.getUsers()
    }
}