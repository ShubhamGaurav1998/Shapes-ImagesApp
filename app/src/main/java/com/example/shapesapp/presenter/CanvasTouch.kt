package com.example.shapesapp.presenter

import android.view.MotionEvent

interface CanvasTouch {
    fun onClickEvent(event: MotionEvent)
    fun onLongPressEvent(initialTouchX: Float, initialTouchY: Float)
}
