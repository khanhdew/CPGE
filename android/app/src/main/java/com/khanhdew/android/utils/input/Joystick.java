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
    protected InnerCircle innerCircle;
    private double playerSpeed;

    public Joystick(GamePanel gamePanel) {
        super(gamePanel);
        init();
    }

    private void init() {
        xCenter = 300;
        yCenter = 800;
        r = 200;
        innerCircle = new InnerCircle(gamePanel, xCenter, yCenter, 80);
        paint.setColor(Color.GREEN);
        // chỉ vẽ viền tròn
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        touchDown = false;
        playerSpeed = player.getSpeed();
    }

    @Override
    public boolean touchEvent(MotionEvent event, int pointerId) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE: {
                float x = 0;
                float y = 0;
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int currentPointerId = event.getPointerId(i);
                    if (currentPointerId == this.pointerId) {
                        x = event.getX(i);
                        y = event.getY(i);
                        break;
                    }
                }
                touchDown = true;
                innerCircle.updatePosition(x, y, xCenter, yCenter, r);
                float angle = (float) Math.toDegrees(Math.atan2(y - yCenter, x - xCenter));
                // calculate dx, dy
                dx = (float) (playerSpeed * Math.cos(Math.toRadians(angle)));
                dy = (float) (playerSpeed * Math.sin(Math.toRadians(angle)));
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (this.pointerId == pointerId) {
                    touchDown = false;
                    dx = 0;
                    dy = 0;
                    innerCircle.setX(xCenter);
                    innerCircle.setY(yCenter);
                    this.pointerId = -1;
                }
                break;
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (touchDown) {
            innerCircle.draw(canvas);
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

        public void updatePosition(float touchX, float touchY, float parentX, float parentY, float parentR) {
            float distance = (float) Math.hypot(touchX - parentX, touchY - parentY);
            if (distance > parentR) {
                float angle = (float) Math.atan2(touchY - parentY, touchX - parentX);
                this.xCenter = parentX + parentR * (float) Math.cos(angle);
                this.yCenter = parentY + parentR * (float) Math.sin(angle);
            } else {
                this.xCenter = touchX;
                this.yCenter = touchY;
            }
        }

        public void setX(float x) {
            this.xCenter = x;
        }

        public void setY(float y) {
            this.yCenter = y;
        }
    }

}
