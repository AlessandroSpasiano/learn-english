package it.alexs.learnenglish.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class LearnEnglishBadRequest (message: String): LearnEnglishException(message)