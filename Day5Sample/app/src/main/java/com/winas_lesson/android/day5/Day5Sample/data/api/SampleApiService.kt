package com.winas_lesson.android.day5.Day5Sample.data.api

import com.winas_lesson.android.day5.Day5Sample.data.model.FeedableItem
import com.winas_lesson.android.day5.Day5Sample.data.model.Hospital
import com.winas_lesson.android.day5.Day5Sample.data.model.Restaurant
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface SampleApiService {
    @GET("list-adr.json")
    fun getList(): Deferred<Response<List<FeedableItem>>>

    @GET("hospital-adr.json")
    fun getHospital(): Deferred<Response<Hospital>>

    @GET("restaurant-adr.json")
    fun getRestaurant(): Deferred<Response<Restaurant>>
}
