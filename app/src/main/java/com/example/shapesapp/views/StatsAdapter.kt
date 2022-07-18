package com.example.shapesapp.views

import android.content.Context
import com.example.shapesapp.views.StatsActivity
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.shapesapp.R
import android.view.ViewGroup
import android.widget.LinearLayout
import android.view.LayoutInflater
import android.view.View
import com.example.shapesapp.models.Shape
import java.util.HashMap

class StatsAdapter(myDataset: HashMap<Shape.Type, Int>?, context: StatsActivity) :
    RecyclerView.Adapter<StatsAdapter.ViewHolder>() {
    private var mDataSet: HashMap<Shape.Type, Int>?
    private val mContext: Context

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var mTextView: TextView

        init {
            mTextView = v.findViewById<View>(R.id.textViewStats) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stats_content, parent, false) as LinearLayout
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = mDataSet!!.keys.toTypedArray()[position]
        val stats = " Shape : " + type + "  Count : " + mDataSet!![type]
        holder.mTextView.text = stats
    }

    override fun getItemCount(): Int {
        return if (mDataSet == null) 0 else mDataSet!!.size
    }

    fun setmDataSet(mDataSet: HashMap<Shape.Type, Int>?) {
        this.mDataSet = mDataSet
    }

    init {
        mContext = context
        mDataSet = myDataset
    }
}