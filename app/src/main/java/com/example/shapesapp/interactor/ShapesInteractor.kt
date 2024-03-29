package com.example.shapesapp.interactor

import android.content.Context
import android.util.Log
import com.example.shapesapp.models.Shape
import com.example.shapesapp.utils.Constants
import com.example.shapesapp.views.CustomView
import java.util.*

class ShapesInteractor private constructor() {
    private var mContext: Context? = null
    var canvas: CustomView? = null
    var maxX = 0
    var maxY = 0
    private var actionSequence = 0

    private fun generateRandomXAndY(): IntArray {
        var x: Int
        var y: Int
        var rn = Random()
        var diff = maxX - Constants.RADIUS
        x = rn.nextInt(diff + 1)
        x += Constants.RADIUS
        rn = Random()
        diff = maxY - Constants.RADIUS
        y = rn.nextInt(diff + 1)
        y += Constants.RADIUS
        Log.d(LOG_TAG, " Random x= " + x + "y" + y)
        return intArrayOf(x, y)
    }

    fun addShapeRandom(type: Shape.Type) {
        val randomCordinates = generateRandomXAndY()
        val shape = createShape(type, randomCordinates[0], randomCordinates[1])
        upDateCanvas(shape)
    }

    private fun createShape(type: Shape.Type, x: Int, y: Int): Shape {
        val shape = Shape(x, y, Constants.RADIUS)
        shape.type = type
        return shape
    }

    fun undo() {
        if (historyList.size > 0) {
            actionSequence--
            historyList.removeLast()
            canvas?.historyList = historyList
            canvas?.invalidate()
        }
    }

    private fun upDateCanvas(shape: Shape) {
        Log.d(LOG_TAG, " upDateCanvas " + shape.type + " actiontype = " + actionSequence)
        shape.actionNumber = actionSequence++
        historyList.add(shape)
        canvas?.historyList = historyList
        canvas?.invalidate()
    }

    private var historyList = ArrayList<Shape>()
        get() = Companion.historyList

    fun setHistoryList(historyList: ArrayList<Shape>) {
        this.historyList = historyList
    }

    fun deleteAllByShape() {
        historyList.clear()
    }

    val countByGroup: HashMap<Shape.Type?, Int>
        get() {
            val shapeTypeCountMap = HashMap<Shape.Type?, Int>()
            for (shape in historyList) {
                    val shapeType = shape.type
                    var existingCnt = 0
                    if (shapeTypeCountMap.containsKey(shape.type)) existingCnt =
                        shapeTypeCountMap[shape.type]!!
                    existingCnt++
                    shapeTypeCountMap[shapeType] = existingCnt
            }
            return shapeTypeCountMap
        }

    fun getmContext(): Context? {
        return mContext
    }

    fun setContext(mContext: Context?) {
        this.mContext = mContext
    }

    companion object {
        val instance = ShapesInteractor()
        private const val LOG_TAG = "Shubham"
        private val historyList = ArrayList<Shape>()
    }
}