package com.winas_lesson.android.day5.Day5Sample.util

import android.app.Activity
import android.widget.Toast

fun Activity.showToast(text: String) {
    val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
    toast.show()
}
