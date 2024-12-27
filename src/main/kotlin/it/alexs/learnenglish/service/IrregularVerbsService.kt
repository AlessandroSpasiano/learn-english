package it.alexs.learnenglish.service

import it.alexs.learnenglish.entities.IrregularVerbs
import it.alexs.learnenglish.repository.IrregularVerbsRepository
import org.springframework.stereotype.Service

@Service
class IrregularVerbsService(
    private val irregularVerbsRepository: IrregularVerbsRepository
) {

    fun list(): List<IrregularVerbs> {
        return irregularVerbsRepository.findAll()
    }
}