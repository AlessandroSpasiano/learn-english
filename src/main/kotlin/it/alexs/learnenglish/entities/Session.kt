package it.alexs.learnenglish.entities

import it.alexs.learnenglish.model.SessionResponse
import it.alexs.learnenglish.utils.asLocal
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

@Entity(name = "session")
data class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val userId: String,
    val createdAt: LocalDateTime
)

fun Session.toResponse() = SessionResponse(
    id = id!!,
    user = userId,
    createdAt = createdAt.asLocal()
)