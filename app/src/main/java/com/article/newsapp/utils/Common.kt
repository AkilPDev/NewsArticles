package com.article.newsapp.utils

import android.os.Build
import androidx.annotation.RequiresApi

object Common {

    @RequiresApi(Build.VERSION_CODES.O)
    fun String.formatPublishedDate(): String {
        if(this.isBlank()) return "N/A"

        return try {
            if (this.isNotBlank()) {
                DateTimeUtils.formatPublishedDate(this)
            } else {
                DateTimeUtils.formatPublishedDateLegacy(this)
            }
        }catch (e:Exception){
            ""
        }

    }
}