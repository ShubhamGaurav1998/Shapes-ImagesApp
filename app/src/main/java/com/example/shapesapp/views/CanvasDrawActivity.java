package com.example.shapesapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.shapesapp.R;
import com.example.shapesapp.databinding.ActivityCanvasDrawBinding;
import com.example.shapesapp.models.Shape;
import com.example.shapesapp.presenter.CanvasPresenter;
import com.example.shapesapp.utils.Constants;


public class CanvasDrawActivity extends AppCompatActivity {
    private static final String TAG = CanvasDrawActivity.class.getSimpleName();
    private CustomView canvas = null;
    CanvasPresenter canvasPresenter;
    private int maxY = 800;
    private int maxX = 600;
    private ActivityCanvasDrawBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_canvas_draw);
        this.canvas = binding.canvasDrawView;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        canvasPresenter = new CanvasPresenter(canvas, this);
        setupActionButtons();
        getCanvasWidthAndHeight();
    }

    private void setupActionButtons() {
        binding.btnCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvasPresenter.addShapeRandom(Shape.Type.CIRCLE);
            }
        });

        binding.btnSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvasPresenter.addShapeRandom(Shape.Type.SQUARE);
            }
        });

        binding.btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvasPresenter.undo();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_canvas_draw, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        else if (id == R.id.action_stats) {
            startStatsView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startStatsView() {
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }

    private void getCanvasWidthAndHeight() {
        ViewTreeObserver viewTreeObserver = canvas.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    canvas.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    maxY = canvas.getHeight();
                    maxX = canvas.getWidth();
                    canvasPresenter.setMaxX(maxX - Constants.RADIUS);
                    int bottomButtonHeight = 100;
                    canvasPresenter.setMaxY(maxY - Constants.RADIUS - bottomButtonHeight);
                    removeOnGlobalLayoutListener(canvas, this);
                    Log.d(TAG, " Screen max x= " + maxX + " maxy = " + maxY);
                }
            });
        }
    }

    public static void removeOnGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener listener) {
        v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    }
}
