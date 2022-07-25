package com.example.shapesapp.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.example.shapesapp.models.Shape
import com.example.shapesapp.utils.Constants

class CustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val RADIUS = Constants.RADIUS
    private var canvas: Canvas? = null
    var historyList: List<Shape> = ArrayList()
    private var drawPaint: Paint? = null
    private fun setupPaint() {
        drawPaint = Paint()
        drawPaint?.color = Color.BLUE
        drawPaint?.isAntiAlias = true
        drawPaint?.strokeWidth = 5f
        drawPaint?.style = Paint.Style.FILL_AND_STROKE
        drawPaint?.strokeJoin = Paint.Join.ROUND
        drawPaint?.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        this.canvas = canvas
        for (shape in historyList) {
                when (shape.type) {
                    Shape.Type.CIRCLE -> {
                        drawPaint?.color = Color.BLUE
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


    var squareSideHalf = 1 / Math.sqrt(2.0)
    fun drawRectangle(x: Int, y: Int) {
        drawPaint?.color = Color.RED
        val rectangle = Rect(
            (x - squareSideHalf * RADIUS).toInt(),
            (y - squareSideHalf * RADIUS).toInt(),
            (x + squareSideHalf * RADIUS).toInt(),
            (y + squareSideHalf * RADIUS).toInt()
        )
        canvas?.drawRect(rectangle, drawPaint!!)
    }


    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
    }
}