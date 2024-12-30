package it.alexs.learnenglish.repository

import it.alexs.learnenglish.entities.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionRepository: JpaRepository<Session, Long> {
}