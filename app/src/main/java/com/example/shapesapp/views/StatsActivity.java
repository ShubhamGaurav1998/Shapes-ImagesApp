package com.example.shapesapp.views;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shapesapp.R;
import com.example.shapesapp.adapter.RecyclerViewEmptyObserver;
import com.example.shapesapp.models.Shape;
import com.example.shapesapp.presenter.StatsPresenter;

import java.util.HashMap;


public class StatsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StatsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView statsEmptyView;
    StatsPresenter statsPresenter;
    private HashMap<Shape.Type, Integer> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.stats_recycler_view);
        statsEmptyView = (TextView) findViewById(R.id.emptyView);
        statsPresenter = new StatsPresenter();
        setupStatsListView();
    }

    private void setupStatsListView() {

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = (HashMap<Shape.Type, Integer>) statsPresenter.getCountByGroup();
        statsEmptyView.setVisibility(View.GONE);
        mAdapter = new StatsAdapter(myDataset, this, onClickDelete);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.registerAdapterDataObserver(new RecyclerViewEmptyObserver(mRecyclerView, statsEmptyView));
    }

    StatsAdapter.OnItemClicked onClickDelete = new StatsAdapter.OnItemClicked() {
        @Override
        public void onItemClick(int position) {
            Shape.Type type = (Shape.Type) myDataset.keySet().toArray()[position];
            Log.d("canvas1234", "   on item click : type " + type);
            statsPresenter.deleteAllByShape(type);
            Toast.makeText(StatsActivity.this, " All "+type+"s deleted. ", Toast.LENGTH_SHORT).show();
            refreshData();
        }
    };

    private void refreshData() {
        myDataset = (HashMap<Shape.Type, Integer>) statsPresenter.getCountByGroup();
        mAdapter.setmDataSet(myDataset);
        mAdapter.notifyDataSetChanged();
    }

}
