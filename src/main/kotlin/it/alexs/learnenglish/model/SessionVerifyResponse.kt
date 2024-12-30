package it.alexs.learnenglish.model

import it.alexs.learnenglish.entities.IrregularVerbs
import it.alexs.learnenglish.entities.Session

data class SessionVerifyResponse(
    val session: Session,
    val result: List<Result>
)

data class Result(
    val irregularVerbs: String,
    val answerPastSimple: DetailAnswer,
    val answerPastParticiple: DetailAnswer
)

data class DetailAnswer(
    val answer: String,
    val correct: Boolean,
    val correctAnswer: String
)