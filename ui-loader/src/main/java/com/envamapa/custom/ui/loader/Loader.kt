package com.envamapa.custom.ui.loader

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout

class Loader : FrameLayout {

    private var _type: Int? = null

    var type: Int?
        get() = _type
        set(value) {
            _type = value
            invalidateLoader()
        }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val attributes = context.obtainStyledAttributes(
            attrs, R.styleable.Loader, defStyle, 0
        )

        _type = attributes.getInt(
            R.styleable.Loader_loader_type, DEFAULT
        )

        attributes.recycle()
        invalidateLoader()
    }

    private fun invalidateLoader() {
        val inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        when (type) {
            HOME -> inflater.inflate(R.layout.home_loader, this, true)
            SEARCH -> inflater.inflate(R.layout.search_loader, this, true)
            else -> inflater.inflate(R.layout.default_loader, this, true)
        }
    }

    companion object {
        const val DEFAULT = 0
        const val HOME = 1
        const val SEARCH = 2
    }
}
