package com.example.shapesapp.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewEmptyObserver

(private val recyclerView: RecyclerView, private val emptyView: TextView?) : RecyclerView.AdapterDataObserver() {

    init {
        checkIfEmpty()
    }

    private fun checkIfEmpty() {

        if (emptyView != null && recyclerView.adapter != null) {

            val emptyViewVisible = recyclerView.adapter!!.itemCount == 0
            emptyView.visibility = if (emptyViewVisible) View.VISIBLE else View.GONE
            recyclerView.visibility = if (emptyViewVisible) View.GONE else View.VISIBLE
        }
    }

    override fun onChanged() {
        checkIfEmpty()
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        checkIfEmpty()
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        checkIfEmpty()
    }

}
