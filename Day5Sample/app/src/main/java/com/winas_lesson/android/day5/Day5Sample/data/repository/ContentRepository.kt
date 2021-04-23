package com.winas_lesson.android.day5.Day5Sample.data.repository

import android.os.Handler
import android.os.Looper
import com.winas_lesson.android.day5.Day5Sample.data.model.FeedableItem
import com.winas_lesson.android.day5.Day5Sample.data.model.Hospital
import com.winas_lesson.android.day5.Day5Sample.data.model.Restaurant
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class ContentRepository {
    fun getHospital(
        hospitalId: Int,
        completion: (hospital: Hospital) -> Unit,
        failure: () -> Unit
    ) {
        val response = Repository.apiInterface.getHospital()
        GlobalScope.async {
            response.await()
            Handler(Looper.getMainLooper()).post {
                val response = response.getCompleted()
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        completion(data)
                    } else {
                        failure()
                    }
                } else {
                    failure()
                }
            }
        }
    }
    fun getRestaurant(
        restaurantId: Int,
        completion: (restaurant: Restaurant) -> Unit,
        failure: () -> Unit
    ) {
        val response = Repository.apiInterface.getRestaurant()
        GlobalScope.async {
            response.await()
            Handler(Looper.getMainLooper()).post {
                val response = response.getCompleted()
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        completion(data)
                    } else {
                        failure()
                    }
                } else {
                    failure()
                }
            }
        }
    }
    fun getList(
    completion: (contents: List<FeedableItem>) -> Unit,
    failure: () -> Unit
    ) {
        val response = Repository.apiInterface.getList()
        GlobalScope.async {
            response.await()
            Handler(Looper.getMainLooper()).post {
                val response = response.getCompleted()
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        completion(data)
                    } else {
                        failure()
                    }
                } else {
                    failure()
                }
            }
        }
    }
}
