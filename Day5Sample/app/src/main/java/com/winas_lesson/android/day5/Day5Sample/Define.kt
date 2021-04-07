package com.winas_lesson.android.day5.Day5Sample

import android.graphics.Color

// MARK: - FontAwesomeSolidIcon
// CheatSheet - https://fontawesome.com/cheatsheet
enum class FontAwesomeSolidIcon {
    MapMarker, MapMarkerAlt, MapPin, Hospital, Restaurant, Phone;
    val resourceId: Int
        get() {
            return when (this) {
                MapMarker -> R.string.icon_fa_solid_map_marker
                MapMarkerAlt -> R.string.icon_fa_solid_map_marker_alt
                MapPin -> R.string.icon_fa_solid_map_pin
                Hospital -> R.string.icon_fa_solid_hospital
                Restaurant -> R.string.icon_fa_solid_restaurant
                Phone -> R.string.icon_fa_solid_phone
            }
        }
}

// MARK: - FontAwesomeRegularIcon
// CheatSheet - https://fontawesome.com/cheatsheet/free/regular
enum class FontAwesomeRegularIcon {
    Map;
    val resourceId: Int
        get() {
            return when (this) {
                Map -> R.string.icon_fa_regular_map
            }
        }
}
