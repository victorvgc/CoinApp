package com.example.coinapp.core.extensions

import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss.nnnnnnn'Z'"

fun String.toDate(format: String = DEFAULT_DATE_FORMAT): LocalDate? {
    return try {
        val formatter = DateTimeFormatter.ofPattern(format)

        LocalDate.parse(this, formatter)
    } catch (e: Exception) {
        Timber.e(e)

        null
    }
}
