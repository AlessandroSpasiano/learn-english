package it.alexs.learnenglish.repository

import it.alexs.learnenglish.entities.IrregularVerbs
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IrregularVerbsRepository: JpaRepository<IrregularVerbs, Long> {
}
