package it.alexs.learnenglish.controller

import it.alexs.learnenglish.exceptions.LearnEnglishBadRequest
import it.alexs.learnenglish.exceptions.LearnEnglishNotFound
import it.alexs.learnenglish.model.ErrorMessage
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
class ExceptionHandlingController {


    @ExceptionHandler(LearnEnglishBadRequest::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequest(ex: LearnEnglishBadRequest, request: HttpServletRequest): ErrorMessage {
        return ErrorMessage(
            message = ex.message ?: "Bad Request",
            description = "The request is invalid",
            statusCode = HttpStatus.BAD_REQUEST.value(),
            timestamp = Date()
        )
    }

    @ExceptionHandler(LearnEnglishNotFound::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(ex: LearnEnglishNotFound, request: HttpServletRequest): ErrorMessage {
        return ErrorMessage(
            message = ex.message ?: "Not Found",
            description = "The resource was not found",
            statusCode = HttpStatus.NOT_FOUND.value(),
            timestamp = Date()
        )
    }

}