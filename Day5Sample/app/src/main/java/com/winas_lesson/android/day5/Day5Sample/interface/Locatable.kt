package com.winas_lesson.android.day5.Day5Sample.`interface`

import com.google.android.gms.maps.model.LatLng

interface Locatable {
    var latitude: Double
    var longitude: Double
    val location: LatLng
    val mapColor: Int
}
