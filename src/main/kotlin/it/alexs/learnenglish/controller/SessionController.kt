package it.alexs.learnenglish.controller

import it.alexs.learnenglish.entities.toResponse
import it.alexs.learnenglish.model.*
import it.alexs.learnenglish.service.SessionService
import jakarta.websocket.server.PathParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SessionController(
    private val sessionService: SessionService
) {

    @PostMapping("/session")
    fun newSession(@RequestBody body: SessionRequest): List<SessionCheckResponse> {
        if (!body.isValid()) {
            throw IllegalArgumentException("Invalid request")
        }

        return sessionService.createSession(body.toEntity())
            .map { it.toResponse() }
    }

    @PutMapping("/session/{id}")
    fun updateSession(@RequestBody body: SessionCheckRequest, @PathVariable("id") sessionId: Long): SessionCheckResponse {
        return sessionService.updateSessionCheck(sessionId, body).toResponse()
    }

    @GetMapping("/session-verify/{id}")
    fun verifySession(@PathVariable("id") sessionId: Long): SessionVerifyResponse {
       return sessionService.verifySession(sessionId)
    }
}