package com.rizafu.coachmark

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by RizaFu on 11/7/16.
 */

class CoachMarkOverlay : View {

    private val CIRCLE_ADDITIONAL_RADIUS_RATIO = 1.5
    private var coachMarkHole: MutableList<CoachMarkHole> = mutableListOf()
    private val paint: Paint = Paint().apply {
        isAntiAlias = true
        color = Color.TRANSPARENT
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    fun addRect(view: View, padding: Int, cornerRadius: Int, isCircle: Boolean){
        val rect = Rect()
        view.getGlobalVisibleRect(rect)

        val width = rect.width()
        val height = rect.height()

        if (isCircle) {
            val cx = rect.centerX()
            val cy = rect.centerY()
            val radius = (Math.max(rect.width(), rect.height()) / 2f * CIRCLE_ADDITIONAL_RADIUS_RATIO).toInt()
            coachMarkHole.add(CoachMarkHole(cx, cy, width, height, padding, radius, isCircle))
        } else {
            val x = rect.left
            val y = rect.top
            coachMarkHole.add(CoachMarkHole(x, y, width, height, padding, cornerRadius, isCircle))
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        coachMarkHole.forEach {
            if (it.isCircle) {
                canvas.drawCircle(it.x.toFloat(), it.y.toFloat(), it.radius.toFloat(), paint)
            } else {
                canvas.drawRoundRect(it.rectF, it.radius.toFloat(), it.radius.toFloat(), paint)
            }
        }
    }
}

private class CoachMarkHole(
        val x: Int,
        val y: Int,
        val width: Int,
        val height: Int,
        val padding: Int,
        val radius: Int,
        val isCircle: Boolean,
        val rectF: RectF = RectF(
                (x - padding).toFloat(),
                (y - padding).toFloat(),
                ((x + width) + padding).toFloat(),
                ((y + height) + padding).toFloat())
)
