package it.alexs.learnenglish.model

data class SessionCheckResponse(
    val id: Long,
    val session: SessionResponse,
    val irregularVerb: IrregularVerbsResponse,
    val answerPastSimple: String?,
    val answerPastParticiple: String?
)

