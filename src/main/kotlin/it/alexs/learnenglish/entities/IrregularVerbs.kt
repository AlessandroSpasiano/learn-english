package it.alexs.learnenglish.entities

import it.alexs.learnenglish.model.IrregularVerbsResponse
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "irregular_verbs")
class IrregularVerbs(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val baseForm: String,
    val pastSimple: String,
    val pastParticiple: String,
    val definition: String
)


fun IrregularVerbs.toResponse() = IrregularVerbsResponse(
    id = id,
    baseForm = baseForm,
    pastSimple = pastSimple,
    pastParticiple = pastParticiple
)