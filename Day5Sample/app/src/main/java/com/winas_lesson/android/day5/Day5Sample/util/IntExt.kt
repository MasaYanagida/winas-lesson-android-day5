package com.winas_lesson.android.day5.Day5Sample.util

import com.winas_lesson.android.day5.Day5Sample.App

val Int.dp: Int
    get() {
        val d = App.context.resources.displayMetrics.density
        return (this.toFloat() / d + 0.5).toInt()
    }
val Int.pixel: Int
    get() {
        val d = App.context.resources.displayMetrics.density
        return (this.toFloat() * d + 0.5).toInt()
    }
fun Int.toColorString(): String? {
    var red = Integer.toHexString(this.shr(16) and 0xFF)
    if (red.length == 1) {
        red = "0${red}"
    }
    var green = Integer.toHexString(this.shr(8) and 0xFF)
    if (green.length == 1) {
        green = "0${green}"
    }
    var blue = Integer.toHexString(this and 0xFF)
    if (blue.length == 1) {
        blue = "0${blue}"
    }
    return "${red}${green}${blue}"
}
