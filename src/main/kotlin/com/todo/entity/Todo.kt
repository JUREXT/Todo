package com.todo.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import io.swagger.v3.oas.annotations.Hidden
import jakarta.persistence.*

@Entity
@Table(name = "todo")
@Introspected
@Serdeable
data class Todo(
    @field:Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Hidden
    val id: Long,

    var name: String,

    var description: String? = null,

    @OneToMany(mappedBy = "todo", fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.ALL], orphanRemoval = true)
    @Hidden
    var tasks: List<Task> = mutableListOf()
)