package it.alexs.learnenglish.model

import it.alexs.learnenglish.entities.IrregularVerbs
import it.alexs.learnenglish.entities.Session
import it.alexs.learnenglish.entities.SessionCheck

data class SessionCheckRequest(
    val irregularVerbId: Long,
    val answerPastSimple: String,
    val answerPastParticiple: String
)

fun SessionCheckRequest.toEntity(session: Session, irreegularVerbs: IrregularVerbs): SessionCheck {
    return SessionCheck(
        session = session,
        irregularVerb = irreegularVerbs,
        answerPastSimple = answerPastSimple,
        answerPastParticiple = answerPastParticiple
    )
}

