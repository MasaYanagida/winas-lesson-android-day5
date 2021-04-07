package com.winas_lesson.android.day5.Day5Sample.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.viewbinding.ViewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.winas_lesson.android.day5.Day5Sample.FontAwesomeSolidIcon
import com.winas_lesson.android.day5.Day5Sample.R
import com.winas_lesson.android.day5.Day5Sample.`interface`.Locatable
import com.winas_lesson.android.day5.Day5Sample.`interface`.ViewBindable
import com.winas_lesson.android.day5.Day5Sample.components.map.CustomMarker
import com.winas_lesson.android.day5.Day5Sample.data.repository.Repository
import com.winas_lesson.android.day5.Day5Sample.databinding.ActivityHospitalDetailBinding
import com.winas_lesson.android.day5.Day5Sample.helper.FontAwesomeSolidIconDrawable
import com.winas_lesson.android.day5.Day5Sample.util.pixel
import kotlin.properties.Delegates

class HospitalDetailActivity : AbstractActivity(), ViewBindable {
    companion object {
        private const val EXTRA_HOSPITAL = "EXTRA_HOSPITAL"
        private const val EXTRA_HOSPITAL_ID = "EXTRA_HOSPITAL_ID"
    }
    override lateinit var binding: ViewBinding
    private val hospitalId: Int by lazy { intent.getIntExtra(EXTRA_HOSPITAL_ID, 0) }

    private val iconView: ImageView?
        get() = (binding as? ActivityHospitalDetailBinding)?.iconView
    private val nameLabel: TextView?
        get() = (binding as? ActivityHospitalDetailBinding)?.nameLabel
    private val departmentLabel: TextView?
        get() = (binding as? ActivityHospitalDetailBinding)?.departmentLabel
    private val addressLabel: TextView?
        get() = (binding as? ActivityHospitalDetailBinding)?.addressLabel
    private val phoneLabel: TextView?
        get() = (binding as? ActivityHospitalDetailBinding)?.phoneLabel
    private val mapView: MapView?
        get() = (binding as? ActivityHospitalDetailBinding)?.mapView
    private lateinit var googleMap: GoogleMap
//    private val marker: CustomMarker<Hospital> by lazy {
//        CustomMarker.create(applicationContext, googleMap, hospital)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHospitalDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        iconView?.setImageDrawable(
            FontAwesomeSolidIconDrawable.create(
                applicationContext,
                FontAwesomeSolidIcon.Hospital,
                32.pixel,
                ResourcesCompat.getColor(resources, R.color.hospitalColor, null)
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
        phoneLabel?.setCompoundDrawables(
            FontAwesomeSolidIconDrawable.create(
                applicationContext,
                FontAwesomeSolidIcon.Phone,
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
