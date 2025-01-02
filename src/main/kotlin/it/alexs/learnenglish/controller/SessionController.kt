package it.alexs.learnenglish.controller

import it.alexs.learnenglish.entities.toResponse
import it.alexs.learnenglish.model.*
import it.alexs.learnenglish.service.SessionService
import it.alexs.learnenglish.utils.assertOrBadRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SessionController(
    private val sessionService: SessionService
) {

    @PostMapping("/session")
    fun newSession(@RequestBody body: SessionRequest): ResponseEntity<List<SessionCheckResponse>> {
        assertOrBadRequest(body.isValid(), "Invalid request body")

        val session = sessionService.createSession(body.toEntity())
            .map { it.toResponse() }

        return ResponseEntity.ok(session)
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