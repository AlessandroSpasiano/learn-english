package it.alexs.learnenglish.model

data class IrregularVerbsResponse(
    val id: Long?,
    val baseForm: String,
    val pastSimple: String,
    val pastParticiple: String
)
