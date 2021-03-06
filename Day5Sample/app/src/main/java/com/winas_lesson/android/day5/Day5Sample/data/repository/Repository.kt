package com.winas_lesson.android.day5.Day5Sample.data.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.winas_lesson.android.day5.Day5Sample.`interface`.FeedContentType
import com.winas_lesson.android.day5.Day5Sample.data.api.SampleApiService
import com.winas_lesson.android.day5.Day5Sample.data.interceptor.HeaderInterceptor
import com.winas_lesson.android.day5.Day5Sample.data.interceptor.ResponseInterceptor
import com.winas_lesson.android.day5.Day5Sample.data.model.FeedableItem
import com.winas_lesson.android.day5.Day5Sample.data.model.Hospital
import com.winas_lesson.android.day5.Day5Sample.data.model.Restaurant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class Repository {
    companion object {
        val apiInterface = Retrofit
            .Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(PolymorphicJsonAdapterFactory.of(FeedableItem::class.java, "content_type")
                            .withSubtype(Restaurant::class.java, FeedContentType.Restraunt.index)
                            .withSubtype(Hospital::class.java, FeedContentType.Hospital.index)
                        )
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createOkHttpClient())
            .baseUrl("http://cs267.xbit.jp/~w065038/app/winas/day5/")
            .build()
            .create(SampleApiService::class.java)
        val content = ContentRepository()

        private fun createOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                //.addInterceptor(interceptor)
                .addInterceptor(HeaderInterceptor())
                .addInterceptor(ResponseInterceptor())
                .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                .readTimeout(30000L, TimeUnit.MILLISECONDS)
                .writeTimeout(30000L, TimeUnit.MILLISECONDS)
                .build()
        }
    }
}
