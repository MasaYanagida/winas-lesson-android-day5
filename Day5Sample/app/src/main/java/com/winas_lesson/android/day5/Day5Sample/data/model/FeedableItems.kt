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

sealed class FeedableItem

data class Restaurant(
    var id: Int = 0,
    var name: String = "",
    @Json(name = "category") var categoryId: Int = 0,
    var address: String = "",
    var phone: String = "",
    @Json(name = "photo_url") var photoUrl: String = "",
    override var latitude: Double = 0.0,
    override var longitude: Double = 0.0
) : FeedableItem(), Parcelable, Locatable {
    val category: RestaurantCategory
        get() {
            val categories = RestaurantCategory.values().filter { it.id == categoryId }
            return if (categories.isNotEmpty()) categories[0] else RestaurantCategory.Ramen
        }
    override val location: LatLng
        get() = LatLng(latitude, longitude)
    override val mapColor: Int
        get() = Color.parseColor("#D31C03")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(categoryId)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeString(photoUrl)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }
    override fun describeContents(): Int {
        return 0
    }
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble())

    companion object CREATOR : Parcelable.Creator<Restaurant> {
        override fun createFromParcel(parcel: Parcel): Restaurant {
            return Restaurant(parcel)
        }
        override fun newArray(size: Int): Array<Restaurant?> {
            return arrayOfNulls(size)
        }
    }
}

data class Hospital (
    var id: Int = 0,
    var name: String = "",
    @Json(name = "full_name") var fullName: String = "",
    @Json(name = "departments") var departmentIds: List<Int> = listOf(),
    var president: String = "",
    var address: String = "",
    var phone: String = "",
    var website: String = "",
    override var latitude: Double = 0.0,
    override var longitude: Double = 0.0
) : FeedableItem(), Parcelable, Locatable {
    val departments: List<HospitalDepartment>
        get() {
            var ret: MutableList<HospitalDepartment> = mutableListOf()
            departmentIds.forEach { departmentId ->
                val departments = HospitalDepartment.values().filter { it.id == departmentId }
                if (departments.isNotEmpty()) {
                    ret.add(departments[0])
                }
            }
            return ret
        }
    override val location: LatLng
        get() = LatLng(latitude, longitude)
    override val mapColor: Int
        get() = Color.parseColor("#2683C6")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(fullName)
        parcel.writeIntList(departmentIds)
        parcel.writeString(president)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeString(website)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }
    override fun describeContents(): Int {
        return 0
    }
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createIntList(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble())

    companion object CREATOR : Parcelable.Creator<Hospital> {
        override fun createFromParcel(parcel: Parcel): Hospital {
            return Hospital(parcel)
        }
        override fun newArray(size: Int): Array<Hospital?> {
            return arrayOfNulls(size)
        }
    }
}

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
