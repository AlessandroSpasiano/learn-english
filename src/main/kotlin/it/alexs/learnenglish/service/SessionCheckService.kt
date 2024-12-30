package it.alexs.learnenglish.service

import it.alexs.learnenglish.entities.Session
import it.alexs.learnenglish.entities.SessionCheck
import it.alexs.learnenglish.repository.SessionCheckRepository
import org.springframework.stereotype.Service

@Service
class SessionCheckService(
    private val sessionCheckRepository: SessionCheckRepository,
    private val irregularVerbsService: IrregularVerbsService
) {

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
}