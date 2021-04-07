package com.winas_lesson.android.day5.Day5Sample.util

fun String.toSafeInt(defaultValue: Int = 0): Int {
    return try {
        toInt()
    } catch (e: Exception) {
        defaultValue
    }
}
