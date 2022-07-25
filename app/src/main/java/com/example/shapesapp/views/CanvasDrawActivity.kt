package com.example.shapesapp.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.shapesapp.R
import com.example.shapesapp.databinding.ActivityCanvasDrawBinding
import com.example.shapesapp.models.Shape
import com.example.shapesapp.presenter.CanvasPresenter
import com.example.shapesapp.utils.Constants

class CanvasDrawActivity : AppCompatActivity() {
    private var canvas: CustomView? = null
    var canvasPresenter: CanvasPresenter? = null
    private var maxY = 0
    private var maxX = 0
    private lateinit var binding: ActivityCanvasDrawBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_canvas_draw)
        canvas = binding.canvasDrawView
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        canvasPresenter = CanvasPresenter(canvas!!, this)
        setupActionButtons()
        canvasWidthAndHeight
    }

    private fun setupActionButtons() {
        binding.btnCircle.setOnClickListener { canvasPresenter?.addShapeRandom(Shape.Type.CIRCLE) }
        binding.btnSquare.setOnClickListener { canvasPresenter?.addShapeRandom(Shape.Type.SQUARE) }
        binding.btnUndo.setOnClickListener { canvasPresenter?.undo() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_canvas_draw, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        } else if (id == R.id.action_stats) {
            startStatsView()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startStatsView() {
        val intent = Intent(this, StatsActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        canvasPresenter?.deleteAllShapes()
    }

    private val canvasWidthAndHeight: Unit
        get() {
            val viewTreeObserver = canvas!!.viewTreeObserver
            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        canvas?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
                        maxY = canvas!!.height
                        maxX = canvas!!.width
                        canvasPresenter?.setMaxX(maxX - Constants.RADIUS)
                        val bottomButtonHeight = 200
                        canvasPresenter?.setMaxY(maxY - Constants.RADIUS - bottomButtonHeight)
                        removeOnGlobalLayoutListener(canvas, this)
                        Log.d(TAG, " Screen max x= $maxX maxy = $maxY")
                    }
                })
            }
        }

    companion object {
        private val TAG = CanvasDrawActivity::class.java.simpleName
        fun removeOnGlobalLayoutListener(v: View?, listener: OnGlobalLayoutListener?) {
            v?.viewTreeObserver?.removeOnGlobalLayoutListener(listener)
        }
    }
}