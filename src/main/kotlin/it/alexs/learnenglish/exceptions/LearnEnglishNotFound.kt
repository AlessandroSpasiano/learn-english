package it.alexs.learnenglish.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class LearnEnglishNotFound(message: String): LearnEnglishException(message)