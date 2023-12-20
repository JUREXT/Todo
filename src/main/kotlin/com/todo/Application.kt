package com.todo

import io.micronaut.runtime.Micronaut.run
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
    info = Info(
        title = "Micronaut Todo - JL",
        version = "1.0"
    )
)
object Api {
}

fun main() {
    run()
}