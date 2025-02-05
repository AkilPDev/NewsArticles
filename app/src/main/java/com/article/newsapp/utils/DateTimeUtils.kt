package com.article.newsapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object  DateTimeUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatPublishedDate(publishedAt: String): String {
        val instant = Instant.parse(publishedAt) // Parse ISO date
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")
            .withLocale(Locale.getDefault())
            .withZone(ZoneId.systemDefault()) // Convert to local time zone
        return formatter.format(instant)
    }

    fun formatPublishedDateLegacy(publishedAt: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC") // Convert from UTC

        val outputFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()) // Desired format
        val date: Date? = inputFormat.parse(publishedAt)
        return date?.let { outputFormat.format(it) } ?: "Invalid Date"
    }
}