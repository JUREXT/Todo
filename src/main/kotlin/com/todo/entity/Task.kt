package com.todo.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import io.swagger.v3.oas.annotations.Hidden
import jakarta.persistence.*

@Entity
@Table(name = "task")
@Introspected
@Serdeable
data class Task(
    @field:Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Hidden
    val id: Long,

    var name: String,

    @ManyToOne
    @JoinColumn(name = "todo_id")
    var todo: Todo? = null
)