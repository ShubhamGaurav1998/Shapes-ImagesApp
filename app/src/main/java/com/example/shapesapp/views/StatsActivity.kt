package com.example.shapesapp.views

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.shapesapp.presenter.StatsPresenter
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.shapesapp.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shapesapp.adapter.RecyclerViewEmptyObserver
import com.example.shapesapp.databinding.ActivityStatsBinding
import com.example.shapesapp.models.Shape
import java.util.HashMap

class StatsActivity : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: StatsAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var statsEmptyView: TextView? = null
    var statsPresenter: StatsPresenter? = null
    private var myDataset: HashMap<Shape.Type, Int>? = null
    private var binding: ActivityStatsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stats)
        mRecyclerView = binding?.statsRecyclerView
        statsEmptyView = binding?.emptyView
        statsPresenter = StatsPresenter()
        setupStatsListView()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupStatsListView() {
        mRecyclerView!!.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager
        myDataset = statsPresenter!!.countByGroup as HashMap<Shape.Type, Int>
        statsEmptyView!!.visibility = View.GONE
        mAdapter = StatsAdapter(myDataset, this)
        mRecyclerView!!.adapter = mAdapter
        mAdapter!!.registerAdapterDataObserver(
            RecyclerViewEmptyObserver(
                mRecyclerView!!,
                statsEmptyView
            )
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}