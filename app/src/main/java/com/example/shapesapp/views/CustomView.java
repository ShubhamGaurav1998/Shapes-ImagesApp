package com.example.shapesapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.shapesapp.models.Shape;
import com.example.shapesapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class CustomView extends View {
    private final String TAG = CustomView.class.getSimpleName();
    public final int RADIUS = Constants.RADIUS;
    private Canvas canvas;
    List<Shape> historyList = new ArrayList<>();

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
    }

    private Paint drawPaint;

    private void setupPaint() {
        drawPaint = new Paint();
        drawPaint.setColor(Color.BLUE);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        for (Shape shape : getHistoryList()) {
            if (shape.isVisible()) {
                switch (shape.getType()) {
                    case CIRCLE:
                        drawPaint.setColor(Color.BLUE);
                        canvas.drawCircle(shape.getxCordinate(), shape.getyCordinate(), RADIUS, drawPaint);
                        break;
                    case SQUARE:
                        drawRectangle(shape.getxCordinate(), shape.getyCordinate());
                        break;
                }
            }
        }
    }
    double squareSideHalf = 1 / Math.sqrt(2);

    public void drawRectangle(int x, int y) {
        drawPaint.setColor(Color.RED);
        Rect rectangle = new Rect((int) (x - (squareSideHalf * RADIUS)), (int) (y - (squareSideHalf * RADIUS)), (int) (x + (squareSideHalf * RADIUS)), (int) (y + ((squareSideHalf * RADIUS))));
        canvas.drawRect(rectangle, drawPaint);
    }

    public List<Shape> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<Shape> historyList) {
        this.historyList = historyList;
    }
}
