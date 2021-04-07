package com.winas_lesson.android.day5.Day5Sample.helper

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint
import com.winas_lesson.android.day5.Day5Sample.App
import com.winas_lesson.android.day5.Day5Sample.FontAwesomeRegularIcon
import com.winas_lesson.android.day5.Day5Sample.FontAwesomeSolidIcon
import java.util.HashMap

// MARK: FontIconDrawable

abstract class FontIconDrawable: Drawable() {
    protected var text: String = ""
    protected val context: Context = App.context
    protected val textPaint: TextPaint = TextPaint()
    protected abstract val typeface: Typeface?
    protected var size: Int = 20
    protected var _alpha: Int = 255

    override fun isStateful(): Boolean {
        return true
    }
    override fun setState(stateSet: IntArray): Boolean {
        val oldValue = textPaint.alpha
        val newValue = if (isEnabled(stateSet)) _alpha else _alpha / 2
        textPaint.alpha = newValue
        return oldValue != newValue
    }
    fun isEnabled(stateSet: IntArray): Boolean {
        for (state in stateSet)
            if (state == android.R.attr.state_enabled)
                return true
        return false
    }
    fun alpha(alpha: Int): FontIconDrawable {
        setAlpha(alpha)
        invalidateSelf()
        return this
    }
    override fun draw(canvas: Canvas) {
        textPaint.textSize = bounds.height().toFloat()
        val textBounds = Rect()
        val textValue = text
        textPaint.getTextBounds(textValue, 0, 1, textBounds)
        val textBottom = (bounds.height() - textBounds.height()) / 2f + textBounds.height() - textBounds.bottom
        canvas.drawText(textValue, bounds.width() / 2f, textBottom, textPaint)
    }
    override fun setAlpha(p0: Int) {
        _alpha = p0
        textPaint.alpha = p0
    }
    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }
    override fun setColorFilter(p0: ColorFilter?) {
        textPaint.colorFilter = p0
    }
    override fun getIntrinsicHeight(): Int {
        return size
    }
    override fun getIntrinsicWidth(): Int {
        return size
    }
    protected fun update() {
        textPaint.typeface = typeface
        textPaint.textSize = size.toFloat()
        textPaint.style = Paint.Style.FILL_AND_STROKE
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.isUnderlineText = false
        textPaint.isAntiAlias = true
        setBounds(0, 0, size, size)
        invalidateSelf()
    }
}

// MARK: FontAwesomeSolidIconDrawable

class FontAwesomeSolidIconDrawable: FontIconDrawable() {
    override val typeface: Typeface? = CachedTypeFaces.get(context, "fa-solid-900.ttf")
    companion object {
        fun create(context: Context, brand: FontAwesomeSolidIcon, size: Int, color: Int): FontAwesomeSolidIconDrawable {
            val drawable = FontAwesomeSolidIconDrawable()
            drawable.text = context.getString(brand.resourceId)
            drawable.size = size
            drawable.update()
            drawable.textPaint.color = color
            return drawable
        }
    }
}

// MARK: FontAwesomeRegularIconDrawable

class FontAwesomeRegularIconDrawable: FontIconDrawable() {
    override val typeface: Typeface? = CachedTypeFaces.get(context, "fa-regular-400.ttf")
    companion object {
        fun create(context: Context, brand: FontAwesomeRegularIcon, size: Int, color: Int): FontAwesomeRegularIconDrawable {
            val drawable = FontAwesomeRegularIconDrawable()
            drawable.text = context.getString(brand.resourceId)
            drawable.size = size
            drawable.update()
            drawable.textPaint.color = color
            return drawable
        }
    }
}

// MARK: CachedTypeFaces
// to avoid memory leaks
// see also - https://stackoverflow.com/questions/16901930/memory-leaks-with-custom-font-for-set-custom-font/16902532#16902532
object CachedTypeFaces {
    private val cache = HashMap<String, Typeface>()
    fun get(context: Context, assetPath: String): Typeface? {
        synchronized(cache) {
            try {
                if (!cache.containsKey(assetPath)) {
                    val typeface = Typeface.createFromAsset(context.assets, assetPath)
                    cache[assetPath] = typeface
                    return typeface
                    // https://stackoverflow.com/questions/12128331/how-to-change-fontfamily-of-textview-in-android
//                    ResourcesCompat.getFont(context, R.font.fa_brands_400)?.let {
//                        cache[assetPath] = it
//                        return it
//                    }
//                    return null
                }
            } catch (e: Exception) {
                return null
            }
            return cache[assetPath]
        }
    }
}
