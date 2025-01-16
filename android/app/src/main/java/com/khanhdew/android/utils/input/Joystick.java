package com.khanhdew.android.utils.input;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.khanhdew.android.main.GamePanel;

public class Joystick extends UIComponent {
    private float dx;
    private float dy;
    private boolean touchDown;
    private InnerCircle innerCircle;

    public Joystick(GamePanel gamePanel) {
        super(gamePanel);
        init();
    }

    private void init() {
        xCenter = 200;
        yCenter = 800;
        r = 200;
        innerCircle = new InnerCircle(gamePanel, 200, 800, 80);
        paint.setColor(Color.GREEN);
        // chỉ vẽ viền tròn
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        touchDown = false;
    }

    public boolean touchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                onTouch(event, () -> {
                    touchDown = true;
                    innerCircle.setX(x);
                    innerCircle.setY(y);
                });
                float angle = (float) Math.toDegrees(Math.atan2(y - yCenter, x - xCenter));
                // calculate dx, dy
                dx = (float) (20 * Math.cos(Math.toRadians(angle)));
                dy = (float) (20 * Math.sin(Math.toRadians(angle)));
                break;
            case MotionEvent.ACTION_UP:
                touchDown = false;
                innerCircle.setX(xCenter);
                innerCircle.setY(yCenter);
                break;
        }
        return super.touchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (touchDown) {
//            innerCircle.draw(canvas);
            player.translateXY(dx, dy);
        }
    }

    public static class InnerCircle extends UIComponent {
        public InnerCircle(GamePanel gamePanel, float xCenter, float yCenter, float r) {
            super(gamePanel);
            this.xCenter = xCenter;
            this.yCenter = yCenter;
            this.r = r;
            this.paint = new Paint();
            paint.setColor(Color.GREEN);
        }

        @Override
        public boolean touchEvent(MotionEvent event) {
            return false;
        }

        public void setX(float x) {
            this.xCenter = x;
        }

        public void setY(float y) {
            this.yCenter = y;
        }
    }

}
