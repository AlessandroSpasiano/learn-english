package it.alexs.learnenglish.repository

import it.alexs.learnenglish.entities.IrregularVerbs
import it.alexs.learnenglish.entities.Session
import it.alexs.learnenglish.entities.SessionCheck
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionCheckRepository: JpaRepository<SessionCheck, Long> {

    fun findAllBySession(session: Session): List<SessionCheck>
    fun findBySessionAndIrregularVerb(session: Session, irregularVerb: IrregularVerbs): SessionCheck?
}