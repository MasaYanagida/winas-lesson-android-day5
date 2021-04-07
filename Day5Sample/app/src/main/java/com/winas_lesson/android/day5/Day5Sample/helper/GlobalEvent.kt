package com.winas_lesson.android.day5.Day5Sample.helper

enum class GlobalEventType {
    Success;
}

data class GlobalEvent(val type: GlobalEventType)
