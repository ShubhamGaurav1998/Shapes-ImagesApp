package com.example.shapesapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shapesapp.R
import com.example.shapesapp.models.Shape

class StatsAdapter(private val mDataSet: HashMap<Shape.Type, Int>?) :
    RecyclerView.Adapter<StatsAdapter.ViewHolder>() {
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
        val stats = " Shape : " + type + "  Count : " + mDataSet[type]
        holder.mTextView.text = stats
        Log.d("Shubham", " stats = " + stats + " pos= " + (position - 1))
    }

    override fun getItemCount(): Int {
        return mDataSet?.size ?: 0
    }
}