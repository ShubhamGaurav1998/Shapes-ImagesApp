package com.example.shapesapp.views;

import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

        mRecyclerView = (RecyclerView) findViewById(R.id.stats_recycler_view);
        statsEmptyView = (TextView) findViewById(R.id.emptyView);
        statsPresenter = new StatsPresenter();
        setupStatsListView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupStatsListView() {

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = (HashMap<Shape.Type, Integer>) statsPresenter.getCountByGroup();
        statsEmptyView.setVisibility(View.GONE);
        mAdapter = new StatsAdapter(myDataset, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.registerAdapterDataObserver(new RecyclerViewEmptyObserver(mRecyclerView, statsEmptyView));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
