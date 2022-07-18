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

    val countByGroup: Serializable
        get() = ShapesInteractor.instance.countByGroup

    init {
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

}
