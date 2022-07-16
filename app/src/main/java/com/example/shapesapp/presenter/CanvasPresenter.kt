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
            ShapesInteractor.getInstance().changeShapeOnTouch(event.x, event.y, Constants.ACTION_TRANSFORM)
        }

        override fun onLongPressEvent(initialTouchX: Float, initialTouchY: Float) {
            Log.d(LOG_TAG, " onLongPressEvent done ")
            ShapesInteractor.getInstance().changeShapeOnTouch(initialTouchX, initialTouchY, Constants.ACTION_DELETE)
        }
    }

    val countByGroup: Serializable
        get() = ShapesInteractor.getInstance().countByGroup

    init {
        canvas.canvasTouch = onTouchListener
        initializeUIComponents(canvas, mContext)
    }

    private fun initializeUIComponents(canvas: CustomView, mContext: Context) {
        ShapesInteractor.getInstance().canvas = canvas
        ShapesInteractor.getInstance().setContext(mContext)
    }


    fun setMaxX(maxX: Int) {
        ShapesInteractor.getInstance().maxX = maxX
    }

    fun setMaxY(maxY: Int) {
        ShapesInteractor.getInstance().maxY = maxY
    }

    fun addShapeRandom(type: Shape.Type) {
        ShapesInteractor.getInstance().addShapeRandom(type)
    }

    fun undo() {
        ShapesInteractor.getInstance().undo()
    }

    companion object {
        private val LOG_TAG = CanvasPresenter.javaClass.simpleName
    }

}
