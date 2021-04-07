package com.winas_lesson.android.day5.Day5Sample.data.model

import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.squareup.moshi.Json
import com.winas_lesson.android.day5.Day5Sample.`interface`.Locatable
import com.winas_lesson.android.day5.Day5Sample.util.createIntList
import com.winas_lesson.android.day5.Day5Sample.util.writeIntList

// https://proandroiddev.com/moshi-polymorphic-adapter-is-d25deebbd7c5
// https://star-zero.medium.com/タイプによって構造の異なるjsonをmoshiでパースする-7f8ba366594a

//sealed class FeedableItem

enum class RestaurantCategory(val id: Int) {
    Ramen(1), Italian(2);
    val text: String
        get() {
            return when(this) {
                Ramen -> "ラーメン"
                Italian -> "イタリアン"
            }
        }
}

enum class HospitalDepartment(val id: Int) {
    Ladies(1), `Internal`(2), Respiratory(3), Cardiology(4), Dermatology(5);
    val text: String
        get() {
            return when(this) {
                Ladies -> "婦人科"
                Internal -> "一般内科"
                Respiratory -> "呼吸器内科"
                Cardiology -> "循環器内科"
                Dermatology -> "皮膚科"
            }
        }
}
