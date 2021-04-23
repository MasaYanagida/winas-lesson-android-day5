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
import com.winas_lesson.android.day5.Day5Sample.data.model.FeedableItem
import com.winas_lesson.android.day5.Day5Sample.data.model.Hospital
import com.winas_lesson.android.day5.Day5Sample.data.model.Restaurant
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
    private val adapter by lazy { FeedListAdapter(applicationContext) }
    private val recyclerView: RecyclerView?
        get() = (binding as? ActivityMainBinding)?.recyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private var items: List<FeedableItem> by Delegates.observable(listOf()) { _, _, _ ->
        adapter.items = items
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        recyclerView?.adapter = adapter
        adapter.listener = object : FeedListAdapterListener {
            override fun onItemTapped(item: FeedableItem) {
                when (item) {
                    is Hospital -> {
                        val intent = HospitalDetailActivity.createIntent(applicationContext, item)
                        startActivity(intent)
                    }
                    is Restaurant -> {
                        val intent = RestaurantDetailActivity.createIntent(applicationContext, item)
                        startActivity(intent)
                    }
                }
            }
        }
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView?.layoutManager = layoutManager

        Handler().postDelayed({
            Repository.content.getList(
                completion = { contents ->
                    Timber.d("[SERVER] list count= ${contents.size}")
                    contents.forEach { content ->
                        when (content) {
                            is Restaurant -> Timber.d("[SERVER] list Restaurant name= ${content.name}")
                            is Hospital -> Timber.d("[SERVER] list Hospital name= ${content.name}")
                        }
                    }
                    items = contents
                },
                failure = {
                    // do nothing
                }
            )
        }, 1000L)
    }
}

interface FeedListAdapterListener {
    fun onItemTapped(item: FeedableItem)
}
class FeedListAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var listener: FeedListAdapterListener? = null
    var items: List<FeedableItem> by Delegates.observable(listOf()) { _, _, _ ->
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val item = items[viewType]
        return when (item) {
            is Restaurant -> {
                val holder = RestaurantItemViewHolder.create(parent)
                holder.itemView.setOnClickListener {
                    listener?.onItemTapped(item)
                }
                holder
            }
            is Hospital -> {
                val holder = HospitalItemViewHolder.create(parent)
                holder.itemView.setOnClickListener {
                    listener?.onItemTapped(item)
                }
                holder
            }
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RestaurantItemViewHolder -> {
                holder.restaurant = (items[position] as? Restaurant) ?: Restaurant()
            }
            is HospitalItemViewHolder -> {
                holder.hospital = (items[position] as? Hospital) ?: Hospital()
            }
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }
}

class RestaurantItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var binding: RestaurantItemViewBinding
    companion object {
        fun create(parent: ViewGroup): RestaurantItemViewHolder {
            val viewBinding = RestaurantItemViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            val holder = RestaurantItemViewHolder(viewBinding.root)
            holder.binding = viewBinding
            holder.binding.addressLabel.setCompoundDrawables(
                FontAwesomeSolidIconDrawable.create(
                    parent.context,
                    FontAwesomeSolidIcon.MapMarkerAlt,
                    12.pixel,
                    ResourcesCompat.getColor(parent.resources, R.color.attributeColor, null)
                ),
                null,
                null,
                null
            )
            return holder
        }
    }
    var restaurant: Restaurant by Delegates.observable(Restaurant()) { prop, _, _ ->
        updateView()
    }
    private fun updateView() {
        binding.iconView.setImageDrawable(FontAwesomeSolidIconDrawable.create(
            itemView.context,
            FontAwesomeSolidIcon.Restaurant,
            32.pixel,
            ResourcesCompat.getColor(itemView.resources, R.color.restaurantColor, null)
        ))
        binding.nameLabel.text = restaurant.name
        binding.categoryLabel.text = restaurant.category.text
        binding.addressLabel.text = restaurant.address
        Picasso.get()
            .load(restaurant.photoUrl)
            .into(binding.photoView)
    }
}

class HospitalItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var binding: HospitalItemViewBinding
    companion object {
        fun create(parent: ViewGroup): HospitalItemViewHolder {
            val viewBinding = HospitalItemViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            val holder = HospitalItemViewHolder(viewBinding.root)
            holder.binding = viewBinding
            holder.binding.addressLabel.setCompoundDrawables(
                FontAwesomeSolidIconDrawable.create(
                    parent.context,
                    FontAwesomeSolidIcon.MapMarkerAlt,
                    12.pixel,
                    ResourcesCompat.getColor(parent.resources, R.color.attributeColor, null)
                ),
                null,
                null,
                null
            )
            holder.binding.phoneLabel.setCompoundDrawables(
                FontAwesomeSolidIconDrawable.create(
                    parent.context,
                    FontAwesomeSolidIcon.Phone,
                    12.pixel,
                    ResourcesCompat.getColor(parent.resources, R.color.attributeColor, null)
                ),
                null,
                null,
                null
            )
            return holder
        }
    }
    var hospital: Hospital by Delegates.observable(Hospital()) { prop, _, _ ->
        updateView()
    }
    private fun updateView() {
        binding.iconView.setImageDrawable(FontAwesomeSolidIconDrawable.create(
            itemView.context,
            FontAwesomeSolidIcon.Hospital,
            32.pixel,
            ResourcesCompat.getColor(itemView.resources, R.color.hospitalColor, null)
        ))
        binding.nameLabel.text = hospital.name
        binding.departmentLabel.text = hospital.departments.map { it.text }.joinToString(", ")
        binding.addressLabel.text = hospital.address
        binding.phoneLabel.text = hospital.phone
    }
}
