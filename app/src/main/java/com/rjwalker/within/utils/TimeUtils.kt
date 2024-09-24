package com.rjwalker.within.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object TimeUtils {

    fun localDateToIsoString(localDate: LocalDate): String =
        LocalDate.Formats.ISO.format(localDate)

    fun isoStringToLocalDate(isoString: String): LocalDateTime =
        LocalDateTime.parse(isoString)

    fun getCurrentTimeInstant(): Instant =
        Clock.System.now()

    fun instantToLocalDateTime(instant: Instant): LocalDateTime =
        instant.toLocalDateTime(TimeZone.currentSystemDefault())

    fun localDateTimeToInstant(localDateTime: LocalDateTime): Instant =
        localDateTime.toInstant(TimeZone.currentSystemDefault())

    fun getLocalDateTimeInTimeZone(timeZone: TimeZone): LocalDateTime =
        Clock.System.now().toLocalDateTime(timeZone)

    fun stringToLocalDate(string: String): LocalDate =
        LocalDate.parse(string)

}