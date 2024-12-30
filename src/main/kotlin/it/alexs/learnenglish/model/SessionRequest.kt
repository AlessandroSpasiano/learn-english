package it.alexs.learnenglish.model

import it.alexs.learnenglish.entities.Session
import java.time.LocalDateTime

data class SessionRequest(
    val user: String
) {
    fun isValid(): Boolean {
        return user.isNotBlank()
    }
}

fun SessionRequest.toEntity(): Session {
    return Session(
        userId = user,
        createdAt = LocalDateTime.now()
    )
}
