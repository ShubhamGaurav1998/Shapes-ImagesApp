package com.example.shapesapp.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import com.example.shapesapp.presenter.CanvasTouch
import android.view.MotionEvent
import android.view.View
import com.example.shapesapp.models.Shape
import com.example.shapesapp.utils.Constants
import java.util.*

class CustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val TAG = CustomView::class.java.simpleName
    val RADIUS = Constants.RADIUS
    private var canvas: Canvas? = null
    var historyList: List<Shape> = ArrayList()
    var canvasTouch: CanvasTouch? = null
    private var longPressDone = false
    private var drawPaint: Paint? = null
    private fun setupPaint() {
        drawPaint = Paint()
        drawPaint!!.color = Color.BLUE
        drawPaint!!.isAntiAlias = true
        drawPaint!!.strokeWidth = 5f
        drawPaint!!.style = Paint.Style.FILL_AND_STROKE
        drawPaint!!.strokeJoin = Paint.Join.ROUND
        drawPaint!!.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        this.canvas = canvas
        Log.d(TAG, "  onDraw called")
        for (shape in historyList) {
            if (shape.isVisible) {
                when (shape.type) {
                    Shape.Type.CIRCLE -> {
                        drawPaint!!.color = Color.BLUE
                        canvas.drawCircle(
                            shape.getxCordinate().toFloat(),
                            shape.getyCordinate().toFloat(),
                            RADIUS.toFloat(),
                            drawPaint!!
                        )
                    }
                    Shape.Type.SQUARE -> drawRectangle(shape.getxCordinate(), shape.getyCordinate())
                }
            }
        }
    }

    private var longClickActive = false
    var initialTouchX = 0f
    var initialTouchY = 0f
    private var startClickTime: Long = 0
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(LOG_TAG, " ACTION_DOWN")
                initialTouchX = event.x
                initialTouchY = event.y
                longPressDone = false
                if (!longClickActive) {
                    longClickActive = true
                    startClickTime = Calendar.getInstance().timeInMillis
                }
            }
            MotionEvent.ACTION_UP -> {
                Log.d(LOG_TAG, " ACTION_UP")
                val currentTime = Calendar.getInstance().timeInMillis
                val clickDuration = currentTime - startClickTime
                if (clickDuration <= MIN_CLICK_DURATION && !longPressDone) {
                    if (canvasTouch != null) {
                        canvasTouch!!.onClickEvent(event)
                    }
                    longClickActive = false
                    startClickTime = Calendar.getInstance().timeInMillis
                    return false
                }
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(LOG_TAG, " ACTION_MOVE")
                val currentTime = Calendar.getInstance().timeInMillis
                val clickDuration = currentTime - startClickTime
                if (clickDuration >= MIN_CLICK_DURATION) {
                    if (canvasTouch != null) {
                        canvasTouch!!.onLongPressEvent(initialTouchX, initialTouchY)
                    }
                    longClickActive = false
                    longPressDone = true
                    startClickTime = Calendar.getInstance().timeInMillis
                    return false
                }
            }
        }
        return true
    }

    var squareSideHalf = 1 / Math.sqrt(2.0)
    fun drawRectangle(x: Int, y: Int) {
        drawPaint!!.color = Color.RED
        val rectangle = Rect(
            (x - squareSideHalf * RADIUS).toInt(),
            (y - squareSideHalf * RADIUS).toInt(),
            (x + squareSideHalf * RADIUS).toInt(),
            (y + squareSideHalf * RADIUS).toInt()
        )
        canvas!!.drawRect(rectangle, drawPaint!!)
    }

    companion object {
        private val LOG_TAG = CustomView::class.java.simpleName
        private const val MIN_CLICK_DURATION = 1000
    }

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
        Log.d(TAG, "  constructor called")
    }
}