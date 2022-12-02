package com.example.cisc482doodler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DoodleView extends View {

    Context context;
    private Paint brush, canvasBrush;
    private Path path;
    private Canvas doodle;
    private Bitmap bitmap;
    private int paintColor = Color.BLACK;
    static final float TOLERANCE=4;

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupCanvas();
        this.context = context;
    }

    private void setupCanvas() {
        path  = new Path();
        brush = new Paint();
        brush.setAntiAlias(true);
        brush.setColor(paintColor);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeCap(Paint.Cap.ROUND);
        brush.setStrokeWidth(50);
        canvasBrush = new Paint(Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        doodle = new Canvas(bitmap);
    }

    // clears the canvas
    public void clear() {
        path.reset();
        invalidate();
    }

    public void setColor(String color) {
        brush.setColor(Color.parseColor(color));
    }

    public void setStrokeWeight(int weight) {
        brush.setStrokeWidth(weight);
    }

    public void setOpacity(int alpha) {
        brush.setAlpha(alpha);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, brush);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(x,y);
                doodle.drawPath(path, brush);
                break;
        }
        invalidate();
        return true;
    }
}
