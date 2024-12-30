package it.alexs.learnenglish.entities

import it.alexs.learnenglish.model.SessionCheckResponse
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne

@Entity(name = "session_check")
data class SessionCheck(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    val session: Session,

    @OneToOne
    val irregularVerb: IrregularVerbs,

    val answerPastSimple: String,
    val answerPastParticiple: String,
)


fun SessionCheck.toResponse() = SessionCheckResponse(
    id = id!!,
    session = session.toResponse(),
    irregularVerb = irregularVerb.toResponse(),
    answerPastSimple = answerPastSimple,
    answerPastParticiple = answerPastParticiple
)