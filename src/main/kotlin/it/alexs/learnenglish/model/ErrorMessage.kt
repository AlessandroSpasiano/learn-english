package it.alexs.learnenglish.model

import java.util.Date

data class ErrorMessage(
    val message: String,
    val description: String,
    val statusCode: Int,
    val timestamp: Date
)
