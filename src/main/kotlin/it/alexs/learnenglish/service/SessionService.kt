package it.alexs.learnenglish.service

import it.alexs.learnenglish.entities.Session
import it.alexs.learnenglish.entities.SessionCheck
import it.alexs.learnenglish.exceptions.LearnEnglishNotFound
import it.alexs.learnenglish.model.DetailAnswer
import it.alexs.learnenglish.model.Result
import it.alexs.learnenglish.model.SessionCheckRequest
import it.alexs.learnenglish.model.SessionVerifyResponse
import it.alexs.learnenglish.repository.SessionCheckRepository
import it.alexs.learnenglish.repository.SessionRepository
import org.springframework.stereotype.Service

@Service
class SessionService(
    private val sessionRepository: SessionRepository,
    private val sessionCheckRepository: SessionCheckRepository,
    private val irregularVerbsService: IrregularVerbsService
) {
    fun createSession(session: Session): List<SessionCheck> {
        val savedSession = sessionRepository.save(session)
        return createNewSessionCheck(savedSession)
    }

    fun findById(sessionId: Long): Session {
        return sessionRepository.findById(sessionId).orElseThrow { LearnEnglishNotFound("Session id $sessionId not found") }
    }

    fun createNewSessionCheck(session: Session): List<SessionCheck> {
        irregularVerbsService.list()
            .shuffled()
            .takeLast(10)
            .forEach { irregularVerbs ->
                sessionCheckRepository.save(
                    SessionCheck(
                        session = session,
                        irregularVerb = irregularVerbs,
                        answerPastSimple = "",
                        answerPastParticiple = ""
                    )
                )
            }

        return getCheckBySession(session)
    }

    fun getCheckBySession(session: Session): List<SessionCheck> {
        return sessionCheckRepository.findAllBySession(session)
    }

    fun updateSessionCheck(sessionId: Long, sessionCheckRequest: SessionCheckRequest): SessionCheck {
        return sessionCheckRepository.findById(sessionId)
            .orElseThrow { LearnEnglishNotFound("Session id $sessionId not found") }
            .copy(
                answerPastSimple = sessionCheckRequest.answerPastSimple,
                answerPastParticiple = sessionCheckRequest.answerPastParticiple
            ).also {
                sessionCheckRepository.save(it)
            }
    }

    fun verifySession(sessionId: Long): SessionVerifyResponse {
        val session = findById(sessionId)
        val results = sessionCheckRepository.findAllBySession(session)
            .map { it.irregularVerb to it }
            .toMap()
            .map { sessionCheckMap ->
                val detailAnswerPastSimle = DetailAnswer(
                    answer = sessionCheckMap.value.answerPastSimple,
                    correctAnswer = sessionCheckMap.key.pastSimple,
                    correct = sessionCheckMap.key.pastSimple == sessionCheckMap.value.answerPastSimple
                )

                val detailAnswerPastParticiple = DetailAnswer(
                    answer = sessionCheckMap.value.answerPastParticiple,
                    correctAnswer = sessionCheckMap.key.pastParticiple,
                    correct = sessionCheckMap.key.pastParticiple == sessionCheckMap.value.answerPastParticiple
                )

                Result(
                    irregularVerbs = sessionCheckMap.key.baseForm,
                    answerPastSimple = detailAnswerPastSimle,
                    answerPastParticiple = detailAnswerPastParticiple
                )
            }

        return SessionVerifyResponse(
            session = session,
            result = results
        )
    }
}
