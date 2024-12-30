package it.alexs.learnenglish.controller

import it.alexs.learnenglish.entities.toResponse
import it.alexs.learnenglish.model.SessionCheckResponse
import it.alexs.learnenglish.model.SessionRequest
import it.alexs.learnenglish.model.toEntity
import it.alexs.learnenglish.service.SessionCheckService
import it.alexs.learnenglish.service.SessionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SessionController(
    private val sessionService: SessionService,
    private val sessionCheckService: SessionCheckService
) {

    @PostMapping("/session")
    fun newSession(@RequestBody body: SessionRequest): List<SessionCheckResponse> {
        if (!body.isValid()) {
            throw IllegalArgumentException("Invalid request")
        }

        return sessionService.createSession(body.toEntity())
            .map { it.toResponse() }
    }
}