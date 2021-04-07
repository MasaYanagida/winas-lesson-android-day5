package com.winas_lesson.android.day5.Day5Sample.components.map

import android.content.Context
import android.graphics.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.winas_lesson.android.day5.Day5Sample.FontAwesomeSolidIcon
import com.winas_lesson.android.day5.Day5Sample.R
import com.winas_lesson.android.day5.Day5Sample.`interface`.Locatable
import com.winas_lesson.android.day5.Day5Sample.helper.FontAwesomeSolidIconDrawable
import com.winas_lesson.android.day5.Day5Sample.util.pixel
import com.winas_lesson.android.day5.Day5Sample.util.weak
import java.lang.ref.WeakReference

class CustomMarker<T>(private val context: Context) where T: Locatable {
    companion object {
        fun <T: Locatable> create(context: Context, map: GoogleMap, item: T): CustomMarker<T> {
            val marker = CustomMarker<T>(context)
            marker.item = item
            marker.marker = map.addMarker(MarkerOptions().position(item.location).flat(true))
            marker.map = map.weak
            marker.loadIcon()
            return marker
        }
    }
    var marker: Marker? = null
    var item: T? = null
    var map: WeakReference<GoogleMap>? = null

    fun dispose() {
        marker?.let { marker ->
            try {
                marker.remove()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        item = null
    }

    fun loadIcon() {
        try {
            val drawable = FontAwesomeSolidIconDrawable.create(
                context,
                FontAwesomeSolidIcon.MapMarkerAlt,
                48.pixel,
                item?.mapColor ?: ResourcesCompat.getColor(context.resources, R.color.attributeColor, null)
            )
            val bitmap = drawable.toBitmap(48.pixel, 48.pixel, Bitmap.Config.ARGB_8888)
            marker?.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
