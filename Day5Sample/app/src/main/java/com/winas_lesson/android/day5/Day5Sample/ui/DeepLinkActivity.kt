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

        // winas://content/12345
        // NOTICE: debug using adb command
        // adb shell am start -W -a android.intent.action.VIEW -d "winas://restaurant/1234" com.winas_lesson.android.day5.Day5Sample
        when (host) {
            "hospital" -> { // winas://hospital/12345
                val hospitalId = (uri?.lastPathSegment ?: "").toSafeInt()
                Handler().postDelayed({
                    val intent = HospitalDetailActivity.createIntent(applicationContext, null, hospitalId)
                    startActivity(intent)
                }, 200L)
            }
            "restaurant" -> {
                val restaurantId = (uri?.lastPathSegment ?: "").toSafeInt()
                Handler().postDelayed({
                    val intent = RestaurantDetailActivity.createIntent(applicationContext, null, restaurantId)
                    startActivity(intent)
                }, 200L)
            }
        }

        finish()
    }
}
