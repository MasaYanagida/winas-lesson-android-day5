package com.winas_lesson.android.day5.Day5Sample.util

import java.lang.ref.WeakReference

val <T> T.weak
    get() = WeakReference<T>(this)
