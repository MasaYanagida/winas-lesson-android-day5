package com.winas_lesson.android.day5.Day5Sample.data.interceptor

import com.winas_lesson.android.day5.Day5Sample.data.api.StatusCode
import com.winas_lesson.android.day5.Day5Sample.helper.GlobalEvent
import com.winas_lesson.android.day5.Day5Sample.helper.GlobalEventType
import okhttp3.Interceptor
import okhttp3.Response
import org.greenrobot.eventbus.EventBus
import timber.log.Timber

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val res = chain.proceed(chain.request())
        if (res.isSuccessful) {
            res.header("Token", null)?.let { token ->
                if (!token.isBlank()) {
                    //Timber.d("#issue AuthorizationToken updated!! ${token}")
                }
            }
        }
        when (res.code) {
            StatusCode.SUCCESS.code -> EventBus.getDefault().post(GlobalEvent(GlobalEventType.Success))
        }
        return res
    }
}
