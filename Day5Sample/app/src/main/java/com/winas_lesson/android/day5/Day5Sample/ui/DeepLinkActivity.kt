package com.winas_lesson.android.day5.Day5Sample.ui

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import com.winas_lesson.android.day5.Day5Sample.util.toSafeInt

class DeepLinkActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.data
        val host = uri?.host ?: ""

        // NOTICE: debug using adb command
        // adb shell am start -W -a android.intent.action.VIEW -d "winas://hospital/1234" com.winas_lesson.android.day5.Day5Sample

        finish()
    }
}
