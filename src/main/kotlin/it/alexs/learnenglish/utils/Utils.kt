package it.alexs.learnenglish.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

private val LOCAL_DATE_TIME_FORMAT = DateTimeFormatterBuilder()
    .appendValue(ChronoField.DAY_OF_MONTH, 2)
    .appendLiteral('/')
    .appendValue(ChronoField.MONTH_OF_YEAR, 2)
    .appendLiteral('/')
    .appendValue(ChronoField.YEAR, 4)
    .appendLiteral(' ')
    .appendValue(ChronoField.HOUR_OF_DAY, 2)
    .appendLiteral(':')
    .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
    .toFormatter()

fun LocalDateTime.asLocal(): String {
    return this.format(LOCAL_DATE_TIME_FORMAT)
}