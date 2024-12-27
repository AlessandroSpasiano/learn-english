    package it.alexs.learnenglish

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LearnEnglishApplication

fun main(args: Array<String>) {
    runApplication<LearnEnglishApplication>(*args)
}
