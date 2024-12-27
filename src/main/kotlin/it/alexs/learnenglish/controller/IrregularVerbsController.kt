package it.alexs.learnenglish.controller

import it.alexs.learnenglish.entities.IrregularVerbs
import it.alexs.learnenglish.service.IrregularVerbsService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IrregularVerbsController(
    private val irregularVerbsService: IrregularVerbsService
) {

    @GetMapping("/irregular-verbs")
    fun irregularVerbs(): List<IrregularVerbs> {
        return irregularVerbsService.list()
    }
}