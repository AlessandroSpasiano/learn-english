package it.alexs.learnenglish.service

import it.alexs.learnenglish.entities.Session
import it.alexs.learnenglish.entities.SessionCheck
import it.alexs.learnenglish.repository.SessionRepository
import org.springframework.stereotype.Service

@Service
class SessionService(
    private val sessionRepository: SessionRepository,
    private val sessionCheckService: SessionCheckService
) {
    fun createSession(session: Session): List<SessionCheck> {
        val savedSession = sessionRepository.save(session)
        return sessionCheckService.createNewSessionCheck(savedSession)
    }
}
