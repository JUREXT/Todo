package com.todo.externalapi.model

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)

@Serdeable
data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geoLocation: GeoLocation,
)

@Serdeable
data class GeoLocation(
    val latitude: String,
    val longitude: String
)

@Serdeable
data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
)
