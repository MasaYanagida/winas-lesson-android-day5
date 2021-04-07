package com.winas_lesson.android.day5.Day5Sample.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.winas_lesson.android.day5.Day5Sample.App
import com.winas_lesson.android.day5.Day5Sample.helper.GlobalEvent
import com.winas_lesson.android.day5.Day5Sample.helper.GlobalEventType
import com.winas_lesson.android.day5.Day5Sample.util.showToast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber

abstract class AbstractActivity : AppCompatActivity() {
    val isTop: Boolean
        get() {
            return equals(App.topActivity)
        }

    protected var isViewLoaded: Boolean = false
    protected var lastPauseTime: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        isViewLoaded = true
    }

    override fun onResume() {
        super.onResume()
        App.topActivity = this
    }

    override fun onPause() {
        lastPauseTime = (System.currentTimeMillis() / 1000L).toInt()
        if (isTop) App.topActivity = null
        super.onPause()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.repeatCount == 0) {
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
