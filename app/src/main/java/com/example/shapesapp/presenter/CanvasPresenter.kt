package com.example.shapesapp.presenter

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import com.example.shapesapp.interactor.ShapesInteractor
import com.example.shapesapp.models.Shape
import com.example.shapesapp.utils.Constants
import com.example.shapesapp.views.CustomView
import java.io.Serializable


class CanvasPresenter(private val canvas: CustomView, private val mContext: Context) {

    private val onTouchListener = object : CanvasTouch {
        override fun onClickEvent(event: MotionEvent) {
            Log.d(LOG_TAG, " onClickEvent done ")
            ShapesInteractor.instance.changeShapeOnTouch(event.x, event.y, Constants.ACTION_TRANSFORM)
        }

        override fun onLongPressEvent(initialTouchX: Float, initialTouchY: Float) {
            Log.d(LOG_TAG, " onLongPressEvent done ")
            ShapesInteractor.instance.changeShapeOnTouch(initialTouchX, initialTouchY, Constants.ACTION_DELETE)
        }
    }

    val countByGroup: Serializable
        get() = ShapesInteractor.instance.countByGroup

    init {
        canvas.canvasTouch = onTouchListener
        initializeUIComponents(canvas, mContext)
    }

    private fun initializeUIComponents(canvas: CustomView, mContext: Context) {
        ShapesInteractor.instance.canvas = canvas
        ShapesInteractor.instance.setContext(mContext)
    }


    fun setMaxX(maxX: Int) {
        ShapesInteractor.instance.maxX = maxX
    }

    fun setMaxY(maxY: Int) {
        ShapesInteractor.instance.maxY = maxY
    }

    fun addShapeRandom(type: Shape.Type) {
        ShapesInteractor.instance.addShapeRandom(type)
    }

    fun undo() {
        ShapesInteractor.instance.undo()
    }

    companion object {
        private val LOG_TAG = CanvasPresenter.javaClass.simpleName
    }

}
