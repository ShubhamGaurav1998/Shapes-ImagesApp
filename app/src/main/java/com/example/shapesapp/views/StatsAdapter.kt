package com.example.shapesapp.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shapesapp.R;
import com.example.shapesapp.models.Shape;

import java.util.HashMap;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.ViewHolder> {

    private HashMap<Shape.Type, Integer> mDataSet;

    private Context mContext;
    private OnItemClicked onClick;

    public StatsAdapter(HashMap<Shape.Type, Integer> myDataset, StatsActivity context) {
        this.mContext = context;
        this.mDataSet = myDataset;
        this.onClick = onClick;
    }

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textViewStats);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stats_content, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final StatsAdapter.ViewHolder holder,  int position) {


        Shape.Type type = (Shape.Type) mDataSet.keySet().toArray()[position];
        String stats = " Shape : " + type + "  Count : " + mDataSet.get(type);
        holder.mTextView.setText(stats);
        Log.d("canvas1234", " stats = " + stats + " pos= " + (position - 1));
    }

    @Override
    public int getItemCount() {
        if (mDataSet == null)
            return 0;
        return mDataSet.size();
    }

    public void setmDataSet(HashMap<Shape.Type, Integer> mDataSet) {
        this.mDataSet = mDataSet;
    }
}
