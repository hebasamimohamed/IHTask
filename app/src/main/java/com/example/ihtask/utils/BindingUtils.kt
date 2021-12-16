package com.example.ihtask.utils

import java.sql.Timestamp

object BindingUtils {
    fun getElapsedTime(utcTimeString: Long): String {
        var timeElapsedInSeconds = (System.currentTimeMillis() - utcTimeString) / 1000
        return if (timeElapsedInSeconds < 60) {
            "less than 1m"
        } else if (timeElapsedInSeconds < 3600) {
            timeElapsedInSeconds /= 60
            timeElapsedInSeconds.toString() + "m"
        } else if (timeElapsedInSeconds < 86400) {
            timeElapsedInSeconds /= 3600
            timeElapsedInSeconds.toString() + "h"
        } else {
            timeElapsedInSeconds /= 86400
            timeElapsedInSeconds.toString() + "d"
        }
    }

    fun getSourceAndTime(sourceName: String, date: Timestamp): String? {
        return sourceName + " • " + getElapsedTime(date.time)
    }


}