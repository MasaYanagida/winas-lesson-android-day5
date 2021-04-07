package com.winas_lesson.android.day5.Day5Sample.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.viewbinding.ViewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.squareup.picasso.Picasso
import com.winas_lesson.android.day5.Day5Sample.FontAwesomeSolidIcon
import com.winas_lesson.android.day5.Day5Sample.R
import com.winas_lesson.android.day5.Day5Sample.`interface`.ViewBindable
import com.winas_lesson.android.day5.Day5Sample.components.map.CustomMarker
import com.winas_lesson.android.day5.Day5Sample.data.repository.Repository
import com.winas_lesson.android.day5.Day5Sample.databinding.ActivityRestaurantDetailBinding
import com.winas_lesson.android.day5.Day5Sample.helper.FontAwesomeSolidIconDrawable
import com.winas_lesson.android.day5.Day5Sample.util.pixel
import kotlin.properties.Delegates

class RestaurantDetailActivity : AbstractActivity(), ViewBindable {
    companion object {
        private const val EXTRA_RESTAURANT = "EXTRA_RESTAURANT"
        private const val EXTRA_RESTAURANT_ID = "EXTRA_RESTAURANT_ID"
    }
    override lateinit var binding: ViewBinding
    private val restaurantId: Int by lazy { intent.getIntExtra(EXTRA_RESTAURANT_ID, 0) }

    private val photoView: ImageView?
        get() = (binding as? ActivityRestaurantDetailBinding)?.photoView
    private val iconView: ImageView?
        get() = (binding as? ActivityRestaurantDetailBinding)?.iconView
    private val nameLabel: TextView?
        get() = (binding as? ActivityRestaurantDetailBinding)?.nameLabel
    private val categoryLabel: TextView?
        get() = (binding as? ActivityRestaurantDetailBinding)?.categoryLabel
    private val addressLabel: TextView?
        get() = (binding as? ActivityRestaurantDetailBinding)?.addressLabel
    private val mapView: MapView?
        get() = (binding as? ActivityRestaurantDetailBinding)?.mapView
    private lateinit var googleMap: GoogleMap
//    private val marker: CustomMarker<Restaurant> by lazy {
//        CustomMarker.create(applicationContext, googleMap, restaurant)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        iconView?.setImageDrawable(
            FontAwesomeSolidIconDrawable.create(
                applicationContext,
                FontAwesomeSolidIcon.Restaurant,
                32.pixel,
                ResourcesCompat.getColor(resources, R.color.restaurantColor, null)
            )
        )
        addressLabel?.setCompoundDrawables(
            FontAwesomeSolidIconDrawable.create(
                applicationContext,
                FontAwesomeSolidIcon.MapMarkerAlt,
                12.pixel,
                ResourcesCompat.getColor(resources, R.color.attributeColor, null)
            ),
            null,
            null,
            null
        )

        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync { map ->
            map.isIndoorEnabled = false
            googleMap = map
        }
    }
    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }
    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }
    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }
    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }
    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }
    private fun updateView() {

    }
}
