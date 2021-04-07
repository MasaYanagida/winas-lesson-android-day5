package com.winas_lesson.android.day5.Day5Sample

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.squareup.picasso.Picasso
import com.winas_lesson.android.day5.Day5Sample.`interface`.ViewBindable
import com.winas_lesson.android.day5.Day5Sample.data.repository.Repository
import com.winas_lesson.android.day5.Day5Sample.databinding.ActivityMainBinding
import com.winas_lesson.android.day5.Day5Sample.databinding.HospitalItemViewBinding
import com.winas_lesson.android.day5.Day5Sample.databinding.RestaurantItemViewBinding
import com.winas_lesson.android.day5.Day5Sample.helper.FontAwesomeSolidIconDrawable
import com.winas_lesson.android.day5.Day5Sample.ui.AbstractActivity
import com.winas_lesson.android.day5.Day5Sample.ui.HospitalDetailActivity
import com.winas_lesson.android.day5.Day5Sample.ui.RestaurantDetailActivity
import com.winas_lesson.android.day5.Day5Sample.util.pixel
import timber.log.Timber
import kotlin.properties.Delegates

class MainActivity : AbstractActivity(), ViewBindable {
    override lateinit var binding: ViewBinding
    //private val adapter by lazy { FeedListAdapter(applicationContext) }
    private val recyclerView: RecyclerView?
        get() = (binding as? ActivityMainBinding)?.recyclerView
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //recyclerView?.adapter = adapter
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView?.layoutManager = layoutManager
    }
}
