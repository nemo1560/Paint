package com.example.nemo1.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CustomPaint extends View {
    private Paint paint;
    private Path path;
    private int drawColor;
    private int backgroundColor;
    private Canvas mCanvas;
    private Bitmap bitmap;
    float mX,mY;

    public CustomPaint(Context context) {
        this(context,null);
    }

    public CustomPaint(Context context, @Nullable AttributeSet attrs) {
        super(context);
        //set color
        drawColor = ResourcesCompat.getColor(getResources(),R.color.paint,null);
        backgroundColor = ResourcesCompat.getColor(getResources(),R.color.background,null);
        Log.d("colorSet",String.valueOf(drawColor +" "+backgroundColor));
        // Holds the path we are currently drawing.
        path = new Path();
        // Set up the paint with which to draw.
        paint = new Paint();
        paint.setColor(drawColor);
        // Smoothes out edges of what is drawn without affecting shape.
        paint.setAntiAlias(true);
        // Dithering affects how colors with higher-precision device
        // than the are down-sampled.
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(10);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Create bitmap, create canvas with bitmap, fill canvas with color.
        bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(bitmap);
        // Fill the Bitmap with the background color.
        mCanvas.drawColor(backgroundColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Initially the user has not drawn anything
        // so we see only the colored bitmap.
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate(); //invalidate() to repaint
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                break;
        }
        return true;
    }

    private void touchUp() {
        path.reset();
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= 4 || dy >= 4) {
            path.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;

            mCanvas.drawPath(path, paint);
        }
    }

    private void touchStart(float x, float y) {
        path.moveTo(x,y);
        mX = x;
        mY = y;
    }
}
