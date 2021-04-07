package com.winas_lesson.android.day5.Day5Sample.`interface`

enum class FeedContentType(val index: String) {
    Hospital("hospital"), Restraunt("restaurant")
}

interface Feedable {
    val feedContentType: FeedContentType
}
